package com.htuy.gridworld.actors

import com.htuy.common.RegisteredSerial
import com.htuy.gridworld.GridWorldBlock
import com.sbf.eventengine.eventobjects.Entity
import com.sbf.eventengine.eventobjects.Event
import java.io.Serializable

/**
 * A force exists within a block but does not have a location. It gets ticked and may
 * query the block to create whatever messages / changes it wants. Forces always tick after every
 * entity within a block each tick
 */
interface Force : GridWorldActor{
    fun produceEvents() : List<Event<GridWorldBlock>>{return listOf()}
}