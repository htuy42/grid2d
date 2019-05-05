package com.htuy.gridworld.local

import com.google.inject.Inject
import com.htuy.cell.Cell
import com.htuy.event.GridEvent
import com.htuy.griddraw.Drawable
import com.htuy.gridmain.KillSwitch
import com.htuy.gridworld.GridWorld
import com.htuy.gridworld.events.user.UserEventStreamProcessor
import com.htuy.gridworld.initializers.BlockInitializer
import com.htuy.gridworld.locations.LocationFetcher
import java.util.concurrent.BlockingQueue

/**
 * Not actually a remote gridworld. Instead, a wrapper around a local gridworld with thread delays on cell get operations.
 * This is intended to mimic a real remote gridworld where getting cells would be a network operation, so we can
 * develop protocols to deal with this delay and 1. test them without the overhead of starting a full cluster, and 2.
 * test them before we develop the cluster code
 */
class FakeRemote @Inject constructor(
    initializer: BlockInitializer,
    killSwitch: KillSwitch,
    streamHandler: UserEventStreamProcessor
) : GridWorld {
    override fun getFetcher(): LocationFetcher {
        return inner.getFetcher()
    }
    override fun processStream(stream: BlockingQueue<GridEvent>) {
        inner.processStream(stream)
    }
    val inner : GridWorld = LocalGridWorld(initializer,killSwitch,streamHandler)
    override fun getCell(x: Int, y: Int): Cell {
        Thread.sleep(1)
        return inner.getCell(x,y)
    }
    override fun getDrawablesAtCell(x: Int, y: Int): Collection<Drawable> {
        Thread.sleep(1)
        return inner.getDrawablesAtCell(x,y)
    }
}