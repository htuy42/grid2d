package com.htuy.input.inputsuites

import com.htuy.Point
import com.htuy.event.CellKeyedEvent
import com.htuy.input.Direction
import com.htuy.input.MouseButton
import com.htuy.input.UIDriver
import org.lwjgl.input.Keyboard


// arrow keys to move, i and o to zoom in and out
val BASIC_UI_CONTROL_SUITE = UICallbacks(listOf(
    BasicKeyCallback(Keyboard.KEY_UP) {
        it.getViewManger().shiftLocation(Direction.UP)
    },
    BasicKeyCallback(Keyboard.KEY_DOWN) {
        it.getViewManger().shiftLocation(Direction.DOWN)
    },
    BasicKeyCallback(Keyboard.KEY_LEFT) {
        it.getViewManger().shiftLocation(Direction.LEFT)
    },
    BasicKeyCallback(Keyboard.KEY_RIGHT) {
        it.getViewManger().shiftLocation(Direction.RIGHT)
    },
    BasicKeyCallback(Keyboard.KEY_I) {
        it.getViewManger().zoomIn()
    },
    BasicKeyCallback(Keyboard.KEY_O) {
        it.getViewManger().zoomOut()
    },
    BasicKeyCallback(Keyboard.KEY_Q){
        it.kill()
    },
    BasicFallbackKeyCallback(){driver, key ->
        for(point in driver.getViewManger().getAllSelectedCells()){
            driver.addGridEvent(CellKeyedEvent(point,key))
        }
    }
))

/**
 * Basic selection using right left click or left drag to select areas and c to clear selections
 */
val BASIC_CLICK_DRAG_SELECTION_SUITE = UICallbacks(listOf(
    BasicDragCallback(MouseButton.LEFT) { uiDriver: UIDriver, p1: Point, p2: Point ->
        val manager = uiDriver.getViewManger()
        uiDriver.getViewManger().selectArea(manager.toCellGrid(p1), manager.toCellGrid(p2))
    },
    BasicClickCallback(MouseButton.LEFT) { uiDriver: UIDriver, point: Point ->
        val cellGridPoint = uiDriver.getViewManger().toCellGrid(point)
        uiDriver.getViewManger().selectArea(cellGridPoint, cellGridPoint)
    },
    BasicKeyCallback(Keyboard.KEY_C) {
        it.getViewManger().clearSelection()
    }
))