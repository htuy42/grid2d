package com.htuy.gridworld.events.types

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.actors.Character
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.Priority
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.eventobjects.StateMachine

/**
 * Spawn the given character
 */
open class SpawnEvent(override val fromCell: CellAddress, override val toCell: CellAddress, val toSpawn: Character) :
    AbstractCellTargetedEvent() {
    override fun apply(to: StateMachine<GridWorldBlock>) {
        to.machine.holder.addActor(toSpawn)
    }
}