package com.htuy.event

import com.htuy.Point

// a key was pressed on a cell. Eventually this should be replaced, because it implies a dependency between
// the stream handler and the input system, but its fine for now
data class CellKeyedEvent(val cellLocation : Point, val keyPressed : Int) : GridEvent