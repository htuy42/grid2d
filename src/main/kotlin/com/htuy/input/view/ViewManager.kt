package com.htuy.input.view

import com.htuy.Point
import com.htuy.input.Direction

/** manages the part of the grid that is currently in view as far as the ui is concerned */
interface ViewManager {
    /** zoom the view out */
    fun zoomOut()

    /** zoom the view in */
    fun zoomIn()

    /** get the cellGrid location of a given point, given its screen location */
    fun toCellGrid(point: Point): Point

    /** shift the view in the given direction*/
    fun shiftLocation(direction: Direction)

    /**
     * get the actual underlying view to be used for rendering
     */
    fun getCurrentView(): View

    /**
     * Mark the given area as selected
     */
    fun selectArea(start: Point, end: Point)

    /**
     * Clear the current selection (if any), without making a new one
     */
    fun clearSelection()

    fun getAllSelectedCells() : List<Point>
}