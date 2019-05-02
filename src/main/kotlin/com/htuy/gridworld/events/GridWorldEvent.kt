package com.htuy.gridworld.events

import com.htuy.event.GridEvent
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.eventobjects.Event

interface GridWorldEvent : GridEvent, Event<GridWorldBlock> {
    val from : HyperPoint
    val to : HyperPoint
}