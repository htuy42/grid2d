package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell
import com.htuy.gridprovider.initializer.CellInitializer

class LocalArrayCellProvider(val size : Int, val initializer : CellInitializer) : CellProvider {
    val cells = Array(size){x ->
        Array(size){y ->
            initializer.getNextCell(x,y,this)
        }
    }
    override fun getCell(x: Int, y: Int): Cell {
        return cells[x][y]
    }
}