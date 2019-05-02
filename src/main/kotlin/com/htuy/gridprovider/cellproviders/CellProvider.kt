package com.htuy.gridprovider.cellproviders

import com.htuy.cell.Cell

interface CellProvider{
    fun getCell(x : Int, y : Int) : Cell

    fun start(){}
    fun stop(){}

}