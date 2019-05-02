package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell

/**
 * Takes a cell at creation and always returns that cell
 */
class SingleCellProvider(val cell : Cell) : CellProvider {
    override fun getCell(x : Int, y : Int): Cell {
        return cell
    }
}