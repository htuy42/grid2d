package com.htuy

import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Math.abs

data class Point(val x : Int, val y : Int){
    fun delta(dx: Int, dy: Int): Point {
        return Point(x + dx, y + dy)
    }

    fun deltaTo(other : Point) : Pair<Int,Int>{
        return Pair(x - other.x,y - other.y)
    }

    fun absDeltaTo(other : Point) : Pair<Int,Int>{
        return Pair(abs(x - other.x),abs(y - other.y))
    }

    fun manhattanTo(other: Point): Int {
        val (dx,dy) = absDeltaTo(other)
        return dx + dy
    }

    val xF : Float = x.toFloat()
    val yF : Float = y.toFloat()
}

/**
 * A rectangle in 2space
 */
data class Rectangle(val start : Point, val end : Point){
    private val initialX : Int by lazy { min(start.x,end.x)}
    private val initialY : Int by lazy{ min(start.y, end.y)}
    private val endX: Int by lazy { max(start.x,end.x)}
    private val endY : Int by lazy{ max(start.y, end.y)}

    /**
     * Whether the given point lies within the rectangle (border inclusive)
     */
    fun contains(point : Point) : Boolean{
        return contains(point.x,point.y)
    }

    /**
     * Whether the given point lies within the rectangle (border inclusive)
     */
    fun contains(x : Int, y : Int) : Boolean{
        return x >= initialX && x <= endX && y >= initialY && y <= endY
    }

    /**
     * All points contained within the rectangle
     */
    fun getAllPoints() : List<Point>{
        val result = ArrayList<Point>()
        for(x in initialX..endX){
            for(y in initialY..endY){
                result.add(Point(x,y))
            }
        }
        return result
    }
}