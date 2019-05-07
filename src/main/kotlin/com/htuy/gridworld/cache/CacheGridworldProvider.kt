package com.htuy.gridworld.cache

import com.google.common.cache.CacheBuilder
import com.google.inject.Inject
import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.griddraw.Drawable
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.GridWorld
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.MAX_CACHE_UPDATE_FREQUENCY
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.ConcurrentHashMap

class CacheGridworldProvider @Inject constructor(val world: GridWorld) : CellProvider {

    val outRequests = ConcurrentHashMap<HyperPoint, Boolean>()

    val cachedBlocks =
        CacheBuilder.newBuilder().concurrencyLevel(4).maximumSize(200).build<HyperPoint, GridWorldBlock>()

    val updateTime = ConcurrentHashMap<HyperPoint, Long>()

    fun remoteGetBlock(location: HyperPoint) {
        if (!outRequests.containsKey(location) && updateTime.getOrDefault(location, 0) < System.currentTimeMillis()) {
            var did = false
            synchronized(outRequests) {
                if (!outRequests.containsKey(location)) {
                    did = true
                    outRequests.put(location, true)
                    updateTime.put(location, System.currentTimeMillis() + MAX_CACHE_UPDATE_FREQUENCY)
                }
            }
            if (did) {
                launch {
                    val block = world.getFetcher().getBlockByHyperpoint(location)
                    cachedBlocks.put(location, block)
                    outRequests.remove(location)
                }
            }
        }
    }

    fun getBlock(location: HyperPoint): GridWorldBlock? {
        val block = cachedBlocks.getIfPresent(location)
        if (block != null) {
            if (block.generation < world.getGeneration()) {
                remoteGetBlock(location)
            }
            return block
        }
        remoteGetBlock(location)
        return null
    }

    override fun getCell(x: Int, y: Int): Cell {
        val point = CellAddress.fromFlatPoint(Point(x, y))
        val block = getBlock(point.blockAddress)
        if (block != null) {
            return block.getCell(point)
        }
        return GridWorldCell(Material.FIRE)
    }

    override fun getDrawablesAtCell(x: Int, y: Int): Collection<Drawable> {
        val point = CellAddress.fromFlatPoint(Point(x, y))
        val block = getBlock(point.blockAddress)
        if (block != null) {
            return block.holder.getAllAtCell(point.cellLocation)
        }
        return listOf()
    }

    override fun start() {
        world.start()
    }

    override fun stop() {
        world.stop()
    }
}