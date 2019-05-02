package com.htuy.gridworld.initializers

import com.htuy.cell.Cell
import com.htuy.gridprovider.initializer.SimpleCellInitializer
import com.htuy.gridworld.GridWorldCell

interface GridWorldCellInitializer : SimpleCellInitializer{
    override fun getNextCell(x: Int, y: Int): GridWorldCell
}