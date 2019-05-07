package com.htuy.gridworld.local

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.common.collect.Multimaps.synchronizedMultimap
import com.google.inject.Inject
import com.htuy.Point
import com.htuy.event.GridEvent
import com.htuy.griddraw.Drawable
import com.htuy.gridmain.KillSwitch
import com.htuy.gridworld.GridWorld
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.user.UserEventStreamProcessor
import com.htuy.gridworld.initializers.BlockInitializer
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.htuy.gridworld.locations.LocationFetcher
import com.htuy.statistics.RateStatistic
import com.htuy.statistics.StatisticsRecorder
import nl.komponents.kovenant.deferred
import java.lang.Exception
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class LocalGridWorld @Inject constructor(
    val initializer: BlockInitializer,
    val killSwitch: KillSwitch,
    val streamHandler: UserEventStreamProcessor,
    val recorder : StatisticsRecorder
) : GridWorld {
    private var generation : Long = 0
    override fun getGeneration(): Long {
        return generation
    }

    override fun processStream(stream: BlockingQueue<GridEvent>) {
        thread {
            while (!killSwitch.getIsDead()) {
                val item = streamHandler.process(stream.take())
                if (item != null) {
                    synchronized(userItemsForTick) {
                        userItemsForTick.add(item)
                    }
                }
            }
        }
    }

    val userItemsForTick = ArrayList<GridWorldEvent>()

    inner class LocalFetcher : LocationFetcher {
        override suspend fun getBlockByHyperpoint(hyperPoint: HyperPoint): GridWorldBlock {
            if (hyperPoint !in blocks) {
                synchronized(blocks) {
                    if (hyperPoint !in blocks) {
                        blocks[hyperPoint] = initializer.makeNewBlock(this, hyperPoint)
                    }
                }
            }
            return blocks[hyperPoint]!!
        }

        override suspend fun getBlockByHyperpointInit(hyperPoint: HyperPoint): GridWorldBlock? {
            return blocks[hyperPoint]
        }

        override suspend fun getCellByAddress(address: CellAddress): GridWorldCell {
            return getBlockByHyperpoint(address.blockAddress).getCell(address)
        }
    }

    override fun getDrawablesAtCell(x: Int, y: Int): Collection<Drawable> {
        val point = CellAddress.fromFlatPoint(Point(x, y))
        val block = blocks[point.blockAddress] ?: return listOf()
        return block.holder.getAllAtCellCopied(point.cellLocation)
    }


    private val fetcher: LocalFetcher by lazy { LocalFetcher() }

    override fun getFetcher(): LocationFetcher {
        return fetcher
    }

    val blocks: ConcurrentMap<HyperPoint, GridWorldBlock> = ConcurrentHashMap<HyperPoint, GridWorldBlock>()

    // the block messages to be used next tick
    var nextTicksBlockMessages: Multimap<HyperPoint, GridWorldEvent> = HashMultimap.create()


    override fun start() {
        val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 2)
        // how we will keep track of whether we are done
        val i = AtomicInteger(0)
        while (!killSwitch.getIsDead()) {
            // synchronously get the userItems list and clear it for future additions
            val itemsFromUser = synchronized(userItemsForTick) {
                val lst = ArrayList(userItemsForTick)
                userItemsForTick.clear()
                lst
            }
            itemsFromUser.forEach {
                nextTicksBlockMessages.put(it.to, it)
            }
            // fetch all the blocks that exist at the beginning. More might be added, but we will not tick them until next time
            val allBlocks = synchronized(blocks) {
                LinkedBlockingQueue(blocks.values)
            }
            if (allBlocks.size == 0) {
                Thread.sleep(100)
                continue
            }
            val def = deferred<Boolean, Exception>()
            // there will be allBlocks.size tasks to fulfill
            i.set(allBlocks.size)
            val thisTicksBlockMessages = nextTicksBlockMessages
            nextTicksBlockMessages = HashMultimap.create()
            // use a safe view on it for concurrent writing
            val safeNextBlockMessages = synchronizedMultimap(nextTicksBlockMessages)
            for (elt in allBlocks) {
                executor.submit {
                    // get the inbound messages for this block. Safe to do concurrent because we only
                    // ever read from this, never write to it
                    try {
                        val messages = thisTicksBlockMessages.get(elt.ownLocation)
                        // tick this block with the inbound messages and collect the outbound ones
                        val outbound = elt.tick(messages)
                        // store the outbound messages for next tick
                        safeNextBlockMessages.putAll(outbound)
                        // if we decrement i to 0, we are done and so is every other one
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    elt.generation = generation + 1
                    recorder.recordStat(RateStatistic("Cell Processed",1))
                    if (i.decrementAndGet() == 0) {
                        def.resolve(true)
                    }
                }
            }
            recorder.recordStat(RateStatistic("Full Grid Processed",1))
            // wait till we have finished all of them. A makeshift barrier
            def.promise.get()
            generation += 1
        }
    }
}