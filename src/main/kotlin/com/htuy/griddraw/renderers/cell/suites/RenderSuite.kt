package com.htuy.griddraw.renderers.cell.suites

import com.htuy.griddraw.renderers.cell.CellRenderer

interface RenderSuite{
    fun getRenderers() : List<CellRenderer>
}