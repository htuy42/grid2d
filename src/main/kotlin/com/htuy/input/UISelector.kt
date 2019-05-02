package com.htuy.input

import com.htuy.Point


interface UISelectionPointer{
    fun getSelectedCells() : List<Point>

    fun selectPoint(point : Point)

    fun selectPointsBetween()

}