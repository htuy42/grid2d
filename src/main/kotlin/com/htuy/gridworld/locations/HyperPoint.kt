package com.htuy.gridworld.locations

import com.htuy.Point
import com.htuy.gridworld.BLOCK_SIDE_SIZE

data class HyperPoint(val scale : Int, val internalPoint : Point){
    companion object {
        /**
         * Make a hyperpoint from a flat point
         */
        fun fromFlatPoint(point : Point,  scale : Int = BLOCK_SIDE_SIZE) : HyperPoint {
            val pointX = if(point.x >= 0){
                point.x / scale
            } else {
                point.x / scale - 1
            }
            val pointY = if(point.y >= 0){
                point.y / scale
            } else {
                point.y / scale - 1
            }
            return HyperPoint(scale, Point(pointX,pointY))
        }
    }

    /**
     * Get the flat point of a given point assuming it lived inside this hyperpoints block
     */
    fun innerLocationToFlatPoint(innerLocation : Point) : Point{
        return Point(
            innerLocation.x + internalPoint.x * scale,
            innerLocation.y + internalPoint.y * scale)
    }
}