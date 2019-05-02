package com.htuy.gridprovider

import com.htuy.event.GridEvent
import java.util.concurrent.BlockingQueue

interface EventStreamHandler{
    fun processStream(stream : BlockingQueue<GridEvent>)
}