package com.htuy.gridworld.locations

import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell
import kotlinx.coroutines.experimental.runBlocking

interface LocationFetcher : CellProvider{

    suspend fun getBlockByHyperpoint(hyperPoint: HyperPoint) : GridWorldBlock

    suspend fun getCellByAddress(address: CellAddress) : GridWorldCell

    suspend fun getBlockByHyperpointInit(hyperPoint: HyperPoint) : GridWorldBlock?

    override fun getCell(x: Int, y: Int): Cell = runBlocking{
        return@runBlocking getCellByAddress(CellAddress.fromFlatPoint(Point(x,y)))
    }
}