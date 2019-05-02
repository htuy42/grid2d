package com.htuy.input.view

import com.google.inject.Inject
import com.htuy.Point
import com.htuy.Rectangle
import com.htuy.config.MainConfig
import com.htuy.input.Direction
import java.lang.Math.abs

// basic view manager
class ViewManagerImpl @Inject constructor(config: MainConfig) : ViewManager {


    private var x: Int = 0
    private var y: Int = 0
    private var scale: Int = config.defaultScale
    private var screenHeight = config.screenHeight
    // the current selection, in GridCoordinates rather than screen coordinates
    private var selection: Rectangle? = null

    private fun restoreViewValidity() {
        if (x < 0) {
            x = 0
        }
        if (y < 0) {
            y = 0
        }
        if (scale < 1) {
            scale = 1
        }
    }

    override fun clearSelection() {
        selection = null
    }

    override fun selectArea(start: Point, end: Point) {
        selection = Rectangle(start, end)
    }

    override fun getCurrentView(): View {
        return View(Point(x, y), scale, selection)
    }

    override fun zoomOut() {
        scale = ((scale - 1) * .9).toInt()
        restoreViewValidity()
    }

    override fun zoomIn() {
        scale = ((scale + 1) * 1.1).toInt()
        restoreViewValidity()
    }

    override fun shiftLocation(direction: Direction) {
        when (direction) {
            Direction.LEFT -> x -= 1
            Direction.RIGHT -> x += 1
            Direction.UP -> y -= 1
            Direction.DOWN -> y += 1
        }
    }

    override fun toCellGrid(point: Point): Point {
        val resultX = point.x / scale + x
        val resultY = if (y >= 0) {
            screenHeight / scale - (point.y / scale + y)
        } else {
            screenHeight / scale - (point.y / scale - y)
        }

        if (x < 0 && y < 0) {
            println("$point,$resultX,$resultY,$y,$scale,$screenHeight")
        }
        return Point(resultX, resultY - 1)
    }

    override fun getAllSelectedCells(): List<Point> {
        return selection?.getAllPoints() ?: listOf()
    }

}