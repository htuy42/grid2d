package com.htuy.gridprovider.initializer

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material

/**
 * Repeatedly cycles thru the given cells and provides them
 */
class CyclingCellInitializer(val materials : List<Material>) : SimpleCellInitializer{
    override fun getNextCell(x: Int, y: Int): Cell {
        if(!iter.hasNext()){
            iter = materials.iterator()
        }
        return BasicCell(iter.next())
    }
    var iter = materials.iterator()
}