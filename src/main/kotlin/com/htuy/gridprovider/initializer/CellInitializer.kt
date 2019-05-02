package com.htuy.gridprovider.initializer

import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider

interface CellInitializer{
    fun getNextCell(x : Int, y : Int, provider : CellProvider) : Cell
}

interface SimpleCellInitializer : CellInitializer{
    fun getNextCell(x : Int, y : Int) : Cell
    override fun getNextCell(x: Int, y: Int, provider: CellProvider): Cell {
        return getNextCell(x,y)
    }
}