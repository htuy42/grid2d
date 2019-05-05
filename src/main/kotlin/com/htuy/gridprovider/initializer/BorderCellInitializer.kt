package com.htuy.gridprovider.initializer

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material

/**
 * Provides on type of cell at borders (x or y == 0), and another otherwise
 */
class BorderCellInitializer(val border : Material, val fill : Material) : SimpleCellInitializer{
    override fun getNextCell(x: Int, y: Int): Cell {
        return if(x == 0 || y == 0){
            BasicCell(border)
        } else {
            BasicCell(fill)
        }
    }
}