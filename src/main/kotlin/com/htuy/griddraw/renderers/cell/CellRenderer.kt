package com.htuy.griddraw.renderers.cell

import com.htuy.Point
import com.htuy.cell.Cell
import com.htuy.input.view.View
import org.lwjgl.opengl.GL11

interface CellRenderer{

    fun startNextRenderStep(deltaMs : Int, view : View){
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL)
        GL11.glBegin(GL11.GL_QUADS)
    }

    fun endRenderStep(){
        GL11.glEnd()
    }

    /**
     * Render the given cell
     */
    fun renderCell(cell : Cell, atX : Float, atY : Float, scale : Int, info : CellRenderInfo)

    /**
     * Render the given cell
     */
    fun renderCell(cell : Cell, at : Point, scale : Int, info : CellRenderInfo){
        renderCell(cell,at.xF,at.yF,scale,info)
    }
}