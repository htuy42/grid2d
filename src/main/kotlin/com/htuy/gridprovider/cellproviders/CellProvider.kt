package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell
import com.htuy.griddraw.Drawable

interface CellProvider{
    fun getCell(x : Int, y : Int) : Cell
    fun getDrawablesAtCell(x : Int, y : Int) : Collection<Drawable>{
        return listOf()
    }
    fun start(){}
    fun stop(){}

}