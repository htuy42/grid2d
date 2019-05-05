package com.htuy.gridworld.initializers

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.initializer.SimpleCellInitializer
import com.htuy.gridworld.BLOCK_SIDE_SIZE
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.contents.Material

/**
 * A clone of the GridCellInitializer for gridworld
 */
class GridWorldGridCellInitializer(val xMat : Material, val yMat : Material, val elseMat : Material, val gridGranularity : Int = BLOCK_SIDE_SIZE) :
    SimpleCellInitializer {
    override fun getNextCell(x: Int, y: Int): Cell {
        return when {
            x % gridGranularity == 0 -> GridWorldCell(xMat)
            y % gridGranularity == 0 -> GridWorldCell(yMat)
            else -> GridWorldCell(elseMat)
        }
    }

}