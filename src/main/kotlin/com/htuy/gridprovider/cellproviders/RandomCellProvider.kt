package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell
import java.util.*


class RandomCellProvider(val cells : List<Cell>) : CellProvider {
    override fun getCell(x : Int, y : Int): Cell {
        val ind = Random().nextInt(cells.size)
        return cells[ind]
    }
}