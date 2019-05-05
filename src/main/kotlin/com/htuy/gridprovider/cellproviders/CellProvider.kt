package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell
import com.htuy.griddraw.Drawable

/**
 * Provides access to cells and the drawable entities that live in them. Exposed for the purposes
 * of access by a rendering engine
 */
interface CellProvider{
    fun getCell(x : Int, y : Int) : Cell
    fun getDrawablesAtCell(x : Int, y : Int) : Collection<Drawable>{
        return listOf()
    }
    fun start(){}
    fun stop(){}

}