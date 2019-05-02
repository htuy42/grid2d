package com.htuy.gridworld.events.types

import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.Priority
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint

abstract class AbstractEvent() : GridWorldEvent{
    override val priority : Int = Priority.TRANSCENDENT.toInt()
}

abstract class AbstractCellTargetedEvent() : AbstractEvent(){
    abstract val fromCell : CellAddress
    abstract val toCell : CellAddress
    override val from: HyperPoint
        get() = fromCell.blockAddress
    override val to: HyperPoint
        get() = toCell.blockAddress
}