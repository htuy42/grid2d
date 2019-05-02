package com.htuy.gridprovider.initializer

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material

/**
 * Repeatedly cycles thru the given cells and provides them
 */
class BorderCellInitializer(val border : Material, val fill : Material) : SimpleCellInitializer{
    override fun getNextCell(x: Int, y: Int): Cell {
        return if(x == 0){
            BasicCell(border)
        } else {
            BasicCell(fill)
        }
    }
}