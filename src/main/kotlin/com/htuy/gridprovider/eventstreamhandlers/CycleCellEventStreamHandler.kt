package com.htuy.gridprovider.eventstreamhandlers

import com.google.inject.Inject
import com.htuy.event.CellKeyedEvent
import com.htuy.event.GridEvent
import com.htuy.gridmain.KillSwitch
import com.htuy.gridprovider.EventStreamHandler
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material
import org.lwjgl.input.Keyboard
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class CycleCellEventStreamHandler @Inject constructor(val killSwitch: KillSwitch, val provider: CellProvider) :
    EventStreamHandler {
    override fun processStream(stream: BlockingQueue<GridEvent>) {
        thread {
            while (!killSwitch.getIsDead()) {
                val fromStream = stream.poll(500, TimeUnit.MILLISECONDS) ?: continue
                if (fromStream is CellKeyedEvent) {
                    when (fromStream.keyPressed) {
                        Keyboard.KEY_F -> provider.getCell(fromStream.cellLocation.x, fromStream.cellLocation.y)
                            .material = Material.FIRE
                        Keyboard.KEY_W -> provider.getCell(fromStream.cellLocation.x, fromStream.cellLocation.y)
                            .material = Material.WATER
                        Keyboard.KEY_E -> provider.getCell(fromStream.cellLocation.x, fromStream.cellLocation.y)
                            .material = Material.EARTH
                    }
                }
            }
        }
    }
}