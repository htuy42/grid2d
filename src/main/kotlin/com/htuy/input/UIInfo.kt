package com.htuy.input

import com.htuy.Point
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

enum class Direction{

    UP,DOWN,LEFT,RIGHT;

    fun applyTo(prev: Point): Point {
        return when(this){
            UP->prev.delta(0,-1)
            DOWN->prev.delta(0,1)
            LEFT->prev.delta(-1,0)
            RIGHT->prev.delta(1,0)
        }
    }

    companion object {
        val rand : Random by lazy{ Random() }
        fun random() : Direction{
            val rand = rand.nextInt(4)
            return when(rand){
                0->UP
                1->DOWN
                2->LEFT
                3->RIGHT
                else -> {
                    throw IllegalStateException("Impossible!")
                }
            }
        }
    }

}

/**
 * the various buttons on a mouse we might press
 */
enum class MouseButton{
    LEFT,MIDDLE,RIGHT,NONE;
    companion object {
        /**
         * convert lwjgl's button representation to a MouseButton
         */
        fun fromLwjglButton(lwjglButton : Int) : MouseButton{
            return when(lwjglButton){
                -1 -> NONE
                0 -> LEFT
                1 -> RIGHT
                2 -> MIDDLE
                else -> throw IllegalArgumentException("Not a recognized mouse button!")
            }
        }
    }


}