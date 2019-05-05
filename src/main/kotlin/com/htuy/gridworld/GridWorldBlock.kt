package com.htuy.gridworld

import com.google.common.collect.HashMultimap
import com.htuy.Point
import com.htuy.gridworld.actors.ActorHolder
import com.htuy.gridworld.actors.MultimapActorHolder
import com.htuy.gridworld.actors.GridWorldActor
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.EventEngine
import com.sbf.eventengine.EventEngineImpl
import com.sbf.eventengine.eventobjects.ComplicatedEvent
import com.sbf.eventengine.eventobjects.StateMachine
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

val ThreadsEngine: ThreadLocal<EventEngine<GridWorldBlock>> =
    ThreadLocal.withInitial { EventEngineImpl<GridWorldBlock>() }

class GridWorldBlock(val ownLocation : HyperPoint, cells : List<List<GridWorldCell>>) : Serializable, StateMachine<GridWorldBlock> {

    val holder: ActorHolder = MultimapActorHolder()

    // reference is useful for things that take in a machine of type t rather than an actual t
    @Transient
    override val machine: GridWorldBlock = this

    override fun getAllEntities(): Iterable<GridWorldActor> {
        return holder.getAll()
    }

    private val internalCells: ArrayList<ArrayList<GridWorldCell>> = arrayListOf()

    init{
        if(cells.size != BLOCK_SIDE_SIZE || cells[0].size != BLOCK_SIDE_SIZE){
            throw IllegalArgumentException("Must receive sufficient blocks!")
        }
        for(x in 0 until BLOCK_SIDE_SIZE){
            internalCells.add(arrayListOf())
            internalCells[x].addAll(cells[x])
        }
    }

    @Transient
    private val outboundMessages = HashMultimap.create<HyperPoint, GridWorldEvent>()

    fun getCell(location: CellAddress): GridWorldCell {
        return internalCells.get(location.cellLocation.x).get(location.cellLocation.y)
    }

    fun worldGridToInternalGrid(point: Point): Point {
        return Point(point.x % BLOCK_SIDE_SIZE, point.y % BLOCK_SIDE_SIZE)
    }


    /**
     * Receive an event that was created during processing but was decided not to be processed.
     * This in practice means that the even should be exported. There are currently no other acceptable
     * reasons for an event to flag itself as to be dumped
     */
    override fun dumpEventTo(event: ComplicatedEvent<GridWorldBlock>) {
        if(event !is GridWorldEvent){
            throw IllegalStateException("Only know how to deal with GridWorldEvents. Where did this come from?")
        }
        addOutboundEvents(listOf(event))
    }

    /**
     * Tick the block. This performs the following actions, in sequence:
     * 1. Run the event loop for every passed message. Each message will be preprocessed by every
     * entity and force in the block, and then any generated events will be executed. In the process,
     * new events may be generated. This process continues until the event and currentTickMessage queues are empty
     * 2. Tick every entity in the cell, getting all the messages they would generate.
     * 3. Rerun the event loop for every generated message to be handled this iteration at this block
     * 4. Tick every force in the cell, getting all the messages they would generate.
     * 5. Rerun the event loop for every generated message to be handled this iteration at this block
     */
    fun tick(messages: Collection<GridWorldEvent>): HashMultimap<HyperPoint, GridWorldEvent> {
        outboundMessages.clear()
        val engine = ThreadsEngine.get()
        engine.useMachine(this)
        for (elt in messages) {
            engine.addEvent(elt)
        }
        val events = getAllEntities().flatMap { it.tick(this) }
        val allProducedEvents = events.partition { it.to == this.ownLocation }
        addOutboundEvents(allProducedEvents.second)
        for (elt in allProducedEvents.first) {
            engine.addEvent(elt)
        }
        return outboundMessages
    }

    fun addOutboundEvents(events: List<GridWorldEvent>) {
        for (event in events) {
            outboundMessages.put(event.to, event)
        }
    }
}