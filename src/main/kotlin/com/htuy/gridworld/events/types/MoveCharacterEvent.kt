package com.htuy.gridworld.events.types

import com.htuy.Point
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.actors.Character
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.sbf.eventengine.eventobjects.StateMachine
import java.util.*

/**
 * Move a character from one cell to another
 */
open class MoveCharacterEvent private constructor(
    override val fromCell: CellAddress,
    override val toCell: CellAddress,
    val character: Character
) :
    AbstractCellTargetedEvent() {
    companion object {
        /**
         * Create a move event. If mayCrossBlock is false, then if the move would cross the block,
         * it is converted into the "identity move", ie no move occurs at all
         */
        fun makeMoveEvent(
            fromCell: CellAddress,
            toCell: CellAddress,
            char: Character,
            mayCrossBlock: Boolean = true
        ): MoveCharacterEvent {
            if (fromCell.blockAddress != toCell.blockAddress) {
                if (mayCrossBlock) {
                    return MoveCharacterCrossBlockEvent(fromCell, toCell, char)
                } else {
                    return MoveCharacterInBlockEvent(fromCell, fromCell, char)
                }
            } else {
                return MoveCharacterInBlockEvent(fromCell, toCell, char)
            }
        }
    }


    /**
     * Move a character within the given block
     */
    class MoveCharacterInBlockEvent(
        fromCell: CellAddress,
        toCell: CellAddress,
        character: Character
    ) : MoveCharacterEvent(fromCell, toCell, character) {
        override fun applyNoRes(to: StateMachine<GridWorldBlock>) {
            to.machine.holder.moveCharacter(character, fromCell.cellLocation, toCell.cellLocation)
            character.locationInBlock = toCell.cellLocation
        }
    }

    /**
     * Move a character from one block to another, by deleting it from one block and putting it in the other.
     * In practice this means it won't exist at all for some small amount of time.
     * alreadyRemoved is what part of the move character event we are executing. False indicates this is the remove step,
     * and true indicates this is the add step
     */
    class MoveCharacterCrossBlockEvent(
        fromCell: CellAddress,
        toCell: CellAddress,
        character: Character,
        val alreadyRemoved: Boolean = false
    ) : MoveCharacterEvent(fromCell, toCell, character) {
        // if we are in the initial, within block step of the action, to needs to point to this block,
        // since the action will be occurring in this block not the one we are moving to
        override val to: HyperPoint
            get() = if (alreadyRemoved) {
                toCell.blockAddress
            } else {
                fromCell.blockAddress
            }

        override fun apply(to: StateMachine<GridWorldBlock>): List<GridWorldEvent> {
            return if (!alreadyRemoved) {
                to.machine.holder.removeActor(character)
                listOf(MoveCharacterCrossBlockEvent(fromCell, toCell, character, true))
            } else {
                character.locationInBlock = toCell.cellLocation
                to.machine.holder.addActor(character)
                listOf()
            }
        }
    }
}

