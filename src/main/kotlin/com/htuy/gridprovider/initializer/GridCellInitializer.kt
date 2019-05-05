package com.htuy.gridprovider.initializer

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material

/**
 * Provides a grid looking set of cells
 */
class GridCellInitializer(val xMat : Material, val yMat : Material, val elseMat : Material, val gridGranularity : Int) : SimpleCellInitializer{
    override fun getNextCell(x: Int, y: Int): Cell {
        return when {
            x % gridGranularity == 0 -> BasicCell(xMat)
            y % gridGranularity == 0 -> BasicCell(yMat)
            else -> BasicCell(elseMat)
        }
    }

}