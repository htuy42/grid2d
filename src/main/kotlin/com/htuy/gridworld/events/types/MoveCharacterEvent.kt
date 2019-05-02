package com.htuy.gridworld.events.types

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.actors.Character
import com.htuy.gridworld.locations.CellAddress
import com.sbf.eventengine.eventobjects.StateMachine
import java.util.*

/**
 * Move the given character from the given cell to the given cell. Not cross block capable
 */
class MoveCharacterEvent(override val fromCell: CellAddress, override val toCell: CellAddress, val character: Character) :
    AbstractCellTargetedEvent() {
    override fun apply(to: StateMachine<GridWorldBlock>) {
        to.machine.holder.moveCharacter(character,fromCell.cellLocation,toCell.cellLocation)
        character.locationInBlock = toCell.cellLocation
    }
}