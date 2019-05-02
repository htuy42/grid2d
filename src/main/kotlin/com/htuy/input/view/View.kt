package com.htuy.input.view

import com.htuy.Point
import com.htuy.Rectangle


data class View(val start : Point, val scale : Int, val selection : Rectangle?){
    fun isSelected(x : Int, y : Int) : Boolean{
        return selection?.contains(Point(x,y)) ?: false
    }
}