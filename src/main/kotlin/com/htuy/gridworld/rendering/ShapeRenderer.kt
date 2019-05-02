package com.htuy.gridworld.rendering

import com.htuy.Point
import com.htuy.griddraw.Drawable
import com.htuy.griddraw.drawCircle
import com.htuy.griddraw.renderers.cell.CellRenderInfo
import com.htuy.griddraw.setColor
import com.htuy.util.Color
import org.newdawn.slick.opengl.GLUtils

// color is the color to draw the circle. Size is the size of the circle as a float, where 1.0 represents a circle filling
// the whole cell the character occupies, and .5 represents half the cell, etc
class CharacterCircleDrawer(val color : Color = Color.randomColor(),val size : Float = 0.8F, val filled : Boolean = true) : Drawable{
    override fun render(scale: Int, start: Point, info: CellRenderInfo) {
        drawCircle(start.xF,start.yF,size * scale,color,filled)
    }
}