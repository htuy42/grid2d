package com.htuy.gridworld.locations

import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell

interface LocationFetcher : CellProvider{

    fun getBlockByHyperpoint(hyperPoint: HyperPoint) : GridWorldBlock

    fun getCellByAddress(address: CellAddress) : GridWorldCell

    fun getBlockByHyperpointInit(hyperPoint: HyperPoint) : GridWorldBlock?

    override fun getCell(x: Int, y: Int): Cell {
        return getCellByAddress(CellAddress.fromFlatPoint(Point(x,y)))
    }
}