package com.htuy.griddraw.renderers.cell

import com.htuy.cell.Cell
import com.htuy.input.view.View

/**
 * A stack of cell renderers, each called in sequence. In theory, each should
 * handle a different aspect of renderering (ie one for materials, one for borders, etc)
 */
class CellRenderers(subRenderers: List<CellRenderer>) {
    private val subRenderers = subRenderers.toMutableList()
    private var rendererIter = subRenderers.iterator()
    private var currentRenderer: CellRenderer? = null
    fun hasNextRenderer(): Boolean {
        return rendererIter.hasNext()
    }

    fun useNextRenderer(deltaMs: Int, view : View) {
        currentRenderer?.endRenderStep()
        currentRenderer = rendererIter.next()
        currentRenderer!!.startNextRenderStep(deltaMs,view)
    }

    fun reset(isEndOfRenderStep: Boolean = false) {
        if (isEndOfRenderStep) {
            currentRenderer!!.endRenderStep()
        }
        rendererIter = subRenderers.iterator()
    }

    fun renderCell(cell: Cell, atX: Float, atY: Float, scale: Int, info: CellRenderInfo) {
        currentRenderer?.renderCell(cell, atX, atY, scale, info)
            ?: throw IllegalStateException("Called render cell with no current renderer")
    }

    /**
     * Add a single renderer, to the end of our stack
     */
    fun addRenderer(subRenderer: CellRenderer): CellRenderers {
        subRenderers.add(subRenderer)
        reset()
        return this
    }

    /**
     * Append all of the renderers from other, to the end of our stack
     */
    fun with(other: CellRenderers): CellRenderers {
        for (renderer in other.subRenderers) {
            subRenderers.add(renderer)
        }
        reset()
        return this
    }

}