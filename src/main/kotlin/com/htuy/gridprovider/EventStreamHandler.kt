package com.htuy.gridprovider

import com.htuy.event.GridEvent
import java.util.concurrent.BlockingQueue

/**
 * Handles a stream of events by converting each event to useful action
 */
interface EventStreamHandler{
    /**
     * Take in a stream of events and loop on it, repeatedly processing events that come off it
     */
    fun processStream(stream : BlockingQueue<GridEvent>)
}