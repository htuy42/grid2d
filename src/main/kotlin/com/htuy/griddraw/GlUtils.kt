package com.htuy.griddraw

import com.htuy.util.Color
import org.lwjgl.opengl.GL11

private var lastColor : Color? = null

// if we only ever set color using this, it lets us avoid excessive setting. It won't work if other
// set methods are used, though
fun setColor(color : Color){
    if(color != lastColor) {
        GL11.glColor3f(color.r,color.g,color.b)
        lastColor = color
    }
}

fun drawSquare(x : Float, y : Float, scale : Int, color : Color){
    setColor(color)
    GL11.glVertex2f(x,y)
    GL11.glVertex2f(x+scale,y)
    GL11.glVertex2f(x + scale,y+scale)
    GL11.glVertex2f(x,y+scale)
}
