package com.htuy.gridworld.events.user

import com.htuy.event.CellKeyedEvent
import com.htuy.event.GridEvent
import com.htuy.gridworld.contents.characters.test.LightFireCharacter
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.types.SpawnEvent
import com.htuy.gridworld.locations.CellAddress
import org.lwjgl.input.Keyboard

class TestUserEventStreamProcessor : UserEventStreamProcessor {
    override fun process(event: GridEvent): GridWorldEvent? {
        if (event is CellKeyedEvent) {
            when (event.keyPressed) {
                Keyboard.KEY_S -> return SpawnEvent(
                    CellAddress.fromFlatPoint(event.cellLocation),
                    CellAddress.fromFlatPoint(event.cellLocation),
                    LightFireCharacter(CellAddress.fromFlatPoint(event.cellLocation).cellLocation)
                )
            }
        }
        return null
    }
}