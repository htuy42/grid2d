package com.htuy.gridprovider

import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.event.GridEvent
import com.htuy.griddraw.Drawable
import java.util.concurrent.BlockingQueue

interface GridProvider{
    fun getCell(x : Int, y : Int) : Cell
    fun getCell(point : Point) : Cell{
        return getCell(point.x,point.y)
    }
    fun getCellDrawables(x : Int, y : Int) : List<Drawable>
    fun provideEventStream(stream : BlockingQueue<GridEvent>)
    // start the grid provider. May return, but should not be expected to. To end this and force it to return, stop should
    // be called
    fun start()
    fun stop()
}