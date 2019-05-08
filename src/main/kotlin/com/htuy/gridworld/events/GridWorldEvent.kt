package com.htuy.gridworld.events

import com.htuy.common.AbstractSerial
import com.htuy.event.GridEvent
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.eventobjects.ComplicatedEvent
import com.sbf.eventengine.eventobjects.Event
import java.io.Serializable

@AbstractSerial
interface GridWorldEvent : GridEvent, ComplicatedEvent<GridWorldBlock>, Serializable {
    val from : HyperPoint
    val to : HyperPoint
    /**
     * Whether or not the given event should be dumped to the GridWorldBlock (for export),
     * rather than being actually run in the current block.
     */
    override fun shouldDump(machine: GridWorldBlock): Boolean {
        return machine.ownLocation != to
    }
}