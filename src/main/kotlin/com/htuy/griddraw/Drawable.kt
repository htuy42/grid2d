package com.htuy.griddraw

import com.htuy.Point
import com.htuy.griddraw.renderers.cell.CellRenderInfo

interface Drawable{
    fun render(scale : Int, start : Point,info : CellRenderInfo)
}

class CompoundDrawable(val subs : List<Drawable>) : Drawable{
    override fun render(scale: Int, start: Point, info: CellRenderInfo) {
        subs.forEach { it.render(scale,start,info) }
    }
}