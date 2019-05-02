package com.htuy.util


data class Color(val r : Float, val g : Float, val b : Float){
    // takes an rgb and converts to the float representation lwjgl expects
    constructor(r : Int, g : Int, b : Int) : this(r / 255.0f, g / 255.0f, b / 255.0f)
}
