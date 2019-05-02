package com.htuy.gridprovider

import com.google.inject.Inject
import com.htuy.cell.Cell
import com.htuy.event.GridEvent
import com.htuy.griddraw.Drawable
import com.htuy.gridprovider.cellproviders.CellProvider
import java.util.concurrent.BlockingQueue

class GridProviderImpl @Inject constructor(val cellProvider : CellProvider, val streamHandler : EventStreamHandler) : GridProvider{
    override fun getCellDrawables(x: Int, y: Int): Collection<Drawable> {
        return cellProvider.getDrawablesAtCell(x,y)
    }

    override fun getCell(x: Int, y: Int): Cell {
        return cellProvider.getCell(x,y)
    }

    override fun provideEventStream(stream: BlockingQueue<GridEvent>) {
        streamHandler.processStream(stream)
    }

    override fun start() {
        cellProvider.start()
    }

    override fun stop() {
        cellProvider.stop()
    }

}