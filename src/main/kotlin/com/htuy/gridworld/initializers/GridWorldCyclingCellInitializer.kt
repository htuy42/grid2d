package com.htuy.gridworld.initializers

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.initializer.SimpleCellInitializer
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.contents.Material


/**
 * Repeatedly cycles thru the given cells and provides them
 */
class GridWorldCyclingCellInitializer(val materials : List<Material>) : GridWorldCellInitializer {
    override fun getNextCell(x: Int, y: Int): GridWorldCell {
        if(!iter.hasNext()){
            iter = materials.iterator()
        }
        return GridWorldCell(iter.next())
    }
    var iter = materials.iterator()
}