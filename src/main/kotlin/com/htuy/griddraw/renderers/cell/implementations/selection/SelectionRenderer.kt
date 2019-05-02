package com.htuy.griddraw.renderers.cell.implementations.selection

import com.google.inject.Inject
import com.htuy.cell.Cell
import com.htuy.config.GraphicsConfig
import com.htuy.griddraw.renderers.cell.CellRenderInfo
import com.htuy.griddraw.renderers.cell.CellRenderer
import com.htuy.input.view.View
import com.htuy.time.PulsingAnimatable
import com.htuy.griddraw.drawSquare
import org.lwjgl.opengl.GL11

class SelectionRenderer
    @Inject constructor(val graphicsConfig : GraphicsConfig) : CellRenderer {

    companion object {
        private val CELL_FULLNESS = .1f
        private val MIN_PULSE_SIZE = 5
        private val PULSE_FREQUENCE = 750
    }

    private val pulseAnimator = PulsingAnimatable(PULSE_FREQUENCE,1.0f,10.0f)
    private var currentScale = 0f

    override fun startNextRenderStep(deltaMs: Int, view : View) {
        pulseAnimator.update(deltaMs)
        currentScale = pulseAnimator.getCurrentValue()
       GL11.glLineWidth(view.scale * CELL_FULLNESS)
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE)
        GL11.glBegin(GL11.GL_QUADS)
    }


    override fun renderCell(cell: Cell, atX: Float, atY: Float, scale: Int, info: CellRenderInfo) {
        if(info.isSelected){
            drawSquare(
                atX + currentScale / 2,
                atY + currentScale / 2,
                (scale - currentScale).toInt(),
                graphicsConfig.colorsConfig.highlightColor
            )
        }
    }
}