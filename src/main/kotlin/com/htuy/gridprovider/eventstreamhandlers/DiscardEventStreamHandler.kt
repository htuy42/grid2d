package com.htuy.gridprovider.eventstreamhandlers

import com.htuy.event.GridEvent
import com.htuy.gridprovider.EventStreamHandler
import java.util.concurrent.BlockingQueue

class DiscardEventStreamHandler : EventStreamHandler{
    override fun processStream(stream: BlockingQueue<GridEvent>) {
        // do nothing
    }

}