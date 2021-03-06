package com.htuy.gridworld.contents.characters.test

import com.htuy.Point
import com.htuy.griddraw.Drawable
import com.htuy.griddraw.renderers.cell.CellRenderInfo
import com.htuy.gridworld.BLOCK_SIDE_SIZE
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.actors.AbstractCharacter
import com.htuy.gridworld.actors.Character
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.types.ChangeCellMaterialEvent
import com.htuy.gridworld.events.types.MoveCharacterEvent
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.rendering.CharacterCircleDrawer
import com.htuy.input.Direction
import com.sbf.eventengine.eventobjects.Action
import com.sbf.eventengine.eventobjects.Event
import java.util.*

/**
 * Every one second, lights its current cell on fire (sets its material to fire), and moves
 * in a random direction
 */
class LightFireCharacter(override var locationInBlock: Point) : AbstractCharacter(), Drawable by CharacterCircleDrawer(filled = true) {


    // it performs an action once a second
    private var nextAction = System.currentTimeMillis() + 100

    override fun tick(block: GridWorldBlock): List<GridWorldEvent> {
        // if it is time to produce stuff and move
        if (System.currentTimeMillis() > nextAction) {
            nextAction = System.currentTimeMillis() + 100
            val dir = Direction.random()
            val loc = dir.applyTo(CellAddress(block.ownLocation,locationInBlock))
            return listOf(
                ChangeCellMaterialEvent(
                    getOwnLocation(block),
                    getOwnLocation(block),
                    Material.FIRE
                ),
                MoveCharacterEvent.makeMoveEvent(getOwnLocation(block),loc,this,true)
            )
        } else {
            return listOf()
        }
    }

}