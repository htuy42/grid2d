package com.htuy.gridprovider.initializer

import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider

/**
 * Provides cells to a gridProvider when it is asked to provide a cell it hasn't seen before
 */
interface CellInitializer{
    fun getNextCell(x : Int, y : Int, provider : CellProvider) : Cell
}

/**
 * A CellInitializer that doesn't need to take in a provider in order to provide a cell (ie its behavior is
 * static or depends only on x and y
 */
interface SimpleCellInitializer : CellInitializer{
    fun getNextCell(x : Int, y : Int) : Cell
    override fun getNextCell(x: Int, y: Int, provider: CellProvider): Cell {
        return getNextCell(x,y)
    }
}