package com.htuy.gridprovider.initializer

import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridworld.contents.Material

class BasicCellInitializer(val material : Material) : SimpleCellInitializer {
    override fun getNextCell(x: Int, y: Int) : Cell {
        return BasicCell(material)
    }
}