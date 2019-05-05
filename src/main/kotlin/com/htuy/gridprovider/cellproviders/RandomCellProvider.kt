package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell
import java.util.*

/**
 * Everytime a cell is requested, randomly chooses one from the list given at initialization.
 * Note that when used, this produces an epileptic / staticy effect on the screen and has
 * no practical purpose
 */
class RandomCellProvider(val cells : List<Cell>) : CellProvider {
    override fun getCell(x : Int, y : Int): Cell {
        val ind = Random().nextInt(cells.size)
        return cells[ind]
    }
}