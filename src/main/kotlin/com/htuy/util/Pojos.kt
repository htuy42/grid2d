package com.htuy.util

import java.util.*


data class Color(val r : Float, val g : Float, val b : Float){
    fun toSlickColor(): org.newdawn.slick.Color {
        return org.newdawn.slick.Color(r,g,b)
    }

    // takes an rgb and converts to the float representation lwjgl expects
    constructor(r : Int, g : Int, b : Int) : this(r / 255.0f, g / 255.0f, b / 255.0f)

    companion object {
        val rand : Random by lazy{ Random() }
        fun randomColor() : Color{
            return Color(rand.nextFloat(),rand.nextFloat(), rand.nextFloat())
        }
    }
}
