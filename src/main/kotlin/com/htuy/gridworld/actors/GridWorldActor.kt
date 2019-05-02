package com.htuy.gridworld.actors

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.events.GridWorldEvent
import com.sbf.eventengine.eventobjects.Action
import com.sbf.eventengine.eventobjects.Entity
import com.sbf.eventengine.eventobjects.Event
import java.io.Serializable

interface GridWorldActor : Entity<GridWorldBlock>, Serializable {
    fun tick(block : GridWorldBlock) : List<GridWorldEvent>{
        return listOf()
    }

    override fun processEvent(event: Event<GridWorldBlock>, machine: GridWorldBlock): GridWorldEvent? {
        return null
    }

    fun respondToGridAction(action : Action<GridWorldBlock>, machine : GridWorldBlock) : List<GridWorldEvent>{
        return listOf()
    }

    override fun respondToAction(action: Action<GridWorldBlock>, machine: GridWorldBlock): List<GridWorldEvent> {
        val responses = respondToGridAction(action,machine)
        val split = responses.partition { it.to == machine.ownLocation}
        machine.addOutboundEvents(split.second)
        return split.first
    }
}