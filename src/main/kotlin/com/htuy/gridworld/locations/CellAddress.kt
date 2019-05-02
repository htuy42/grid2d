package com.htuy.gridworld.locations

import com.htuy.Point
import com.htuy.gridworld.BLOCK_SIDE_SIZE

data class CellAddress(val blockAddress: HyperPoint, val cellLocation: Point) {
    companion object {
        fun fromFlatPoint(flatPoint: Point, scale: Int = BLOCK_SIDE_SIZE) : CellAddress {
            val cellX = if(flatPoint.x >= 0){
                flatPoint.x % scale
            } else {
                flatPoint.x % scale * -1
            }
            val cellY = if(flatPoint.y >= 0){
                flatPoint.y % scale
            } else {
                flatPoint.y % scale * -1
            }
            return CellAddress(
                HyperPoint.fromFlatPoint(flatPoint, scale),
                Point(cellX,cellY)
            )
        }
    }
}