package com.htuy.gridworld.events.types

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.Priority
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.eventobjects.Event
import com.sbf.eventengine.eventobjects.StateMachine

abstract class AbstractEvent() : GridWorldEvent{
    override val priority : Int = Priority.TRANSCENDENT.toInt()
    override fun apply(to : StateMachine<GridWorldBlock>) : List<GridWorldEvent> {
        applyNoRes(to)
        return listOf()
    }

    /**
     * Override this if you want your application not to produce any sub events.
     * The main use case for producing sub events is if you need to do something in your home block, and something
     * somewhere else. Ie a move event that crosses a border needs to delete from the previous block, and
     * also add to the new block
     */
    open fun applyNoRes(to : StateMachine<GridWorldBlock>){

    }
}

abstract class AbstractCellTargetedEvent() : AbstractEvent(){
    abstract val fromCell : CellAddress
    abstract val toCell : CellAddress
    override val from: HyperPoint
        get() = fromCell.blockAddress
    override val to: HyperPoint
        get() = toCell.blockAddress
}