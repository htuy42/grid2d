package com.htuy.griddraw.renderers.cell

import com.htuy.Point
import com.htuy.cell.BasicCell
import com.htuy.cell.Cell
import com.htuy.gridworld.contents.Material
import com.htuy.input.view.View
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CellRenderersTest{

    lateinit var renderers : CellRenderers

    @Before
    fun setupRenderer(){
        renderers = CellRenderers(listOf())
    }

    @Test
    fun testStandardFunctionalityLoop(){
        val mock = mockk<CellRenderer>(relaxUnitFun = true)
        renderers.addRenderer(mock)
        val view = View(Point(0,0),10,null)
        renderers.useNextRenderer(10,view)
        verify { mock.startNextRenderStep(10,view) }
        assertFalse(renderers.hasNextRenderer())
        val basicCell = BasicCell(Material.EARTH)
        val info = CellRenderInfo(false)
        renderers.renderCell(basicCell,0f,0f,10,info)
        verify{mock.renderCell(basicCell,0f,0f,10,info)}
        renderers.reset(true)
        verify{mock.endRenderStep()}
    }

}