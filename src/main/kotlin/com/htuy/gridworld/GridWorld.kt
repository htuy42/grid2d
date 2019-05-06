package com.htuy.gridworld

import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.event.GridEvent
import com.htuy.gridprovider.EventStreamHandler
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.events.user.UserEventStreamProcessor
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import com.htuy.gridworld.locations.LocationFetcher
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.BlockingQueue
import kotlin.concurrent.thread

/**
 *
 */
interface GridWorld : CellProvider, EventStreamHandler{
    fun getFetcher() : LocationFetcher

    override fun getCell(x: Int, y: Int): Cell = runBlocking{
        return@runBlocking getFetcher().getCellByAddress(CellAddress.fromFlatPoint(Point(x,y)))
    }


    fun getGeneration() : Long
}