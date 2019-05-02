package com.htuy.griddraw.renderers.cell.suites

import com.google.inject.Inject
import com.htuy.griddraw.renderers.cell.CellRenderer
import com.htuy.griddraw.renderers.cell.CellRenderers
import com.htuy.griddraw.renderers.cell.implementations.material.BasicMaterialColorMapper
import com.htuy.griddraw.renderers.cell.implementations.material.BasicMaterialRenderer
import com.htuy.griddraw.renderers.cell.implementations.material.MaterialColorMapper
import com.htuy.griddraw.renderers.cell.implementations.selection.SelectionRenderer
import com.htuy.util.Color

class MaterialCellsBorderSuite @Inject constructor(val materialColorRenderer: BasicMaterialRenderer, val selectionRenderer: SelectionRenderer) : RenderSuite{
    override fun getRenderers(): List<CellRenderer> {
        return listOf(materialColorRenderer,selectionRenderer)
    }
}


