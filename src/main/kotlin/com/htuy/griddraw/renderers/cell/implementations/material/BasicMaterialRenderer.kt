package com.htuy.griddraw.renderers.cell.implementations.material

import com.google.inject.Inject
import com.htuy.cell.Cell
import com.htuy.griddraw.renderers.cell.CellRenderInfo
import com.htuy.griddraw.renderers.cell.CellRenderer
import com.htuy.griddraw.drawSquare

/**
 * Cell renderer that only cares about the material of the cell and ignores everything else
 */
class BasicMaterialRenderer @Inject constructor(val materialColorMapper : MaterialColorMapper) :
    CellRenderer {
    override fun renderCell(cell: Cell, atX: Float, atY: Float, scale: Int, info: CellRenderInfo) {
        val color = materialColorMapper.materialToColor(cell.material)
        drawSquare(atX, atY, scale, color)
    }
}