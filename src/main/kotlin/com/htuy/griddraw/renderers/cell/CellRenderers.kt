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

    /**
     * Whether there are more renderes to use for this render step
     */
    fun hasNextRenderer(): Boolean {
        return rendererIter.hasNext()
    }

    /**
     * Go to the next renderer
     */
    fun useNextRenderer(deltaMs: Int, view : View) {
        currentRenderer?.endRenderStep()
        currentRenderer = rendererIter.next()
        currentRenderer!!.startNextRenderStep(deltaMs,view)
    }

    /**
     * Reset to the first renderer in our list. isEndOfRenderStep should be true if we
     * are calling reset because we have finished a cycle of all the renderers
     */
    fun reset(isEndOfRenderStep: Boolean = false) {
        if (isEndOfRenderStep) {
            currentRenderer!!.endRenderStep()
        }
        rendererIter = subRenderers.iterator()
    }

    /**
     * Render the given cell with the current renderer
     */
    fun renderCell(cell: Cell, atX: Float, atY: Float, scale: Int, info: CellRenderInfo) {
        currentRenderer?.renderCell(cell, atX, atY, scale, info)
            ?: throw IllegalStateException("Called render cell with no current renderer")
    }

    /**
     * Add a single renderer, to the end of our stack. Also resets the stack
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