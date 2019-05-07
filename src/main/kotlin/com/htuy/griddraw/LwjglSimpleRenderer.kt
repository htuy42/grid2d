package com.htuy.griddraw

import com.google.inject.Inject
import com.htuy.Point
import com.htuy.config.MainConfig
import com.htuy.griddraw.renderers.cell.CellRenderInfo
import com.htuy.griddraw.renderers.cell.CellRenderers
import com.htuy.griddraw.renderers.cell.suites.RenderSuite
import com.htuy.gridprovider.GridProvider
import com.htuy.input.view.View
import com.htuy.statistics.RateStatistic
import com.htuy.statistics.StatisticsRecorder
import com.htuy.time.FrameTracker

import org.lwjgl.opengl.Display
import org.lwjgl.opengl.DisplayMode
import org.lwjgl.opengl.GL11
import org.newdawn.slick.Graphics

/**
 * Simple render engine using lwjgl (+ slick) to draw stuff.
 * There seem to be some issues with the slick bit, probably because we are only using it sometimes.
 */
class LwjglSimpleRenderer @Inject constructor(
    val config: MainConfig,
    renderSuite: RenderSuite,
    val tracker: FrameTracker,
    val recorder : StatisticsRecorder
) : GridRenderer {

    companion object {
        lateinit var g : Graphics
    }

    val cellRenderers = CellRenderers(renderSuite.getRenderers())
    override fun initialize() {
        Display.setDisplayMode(DisplayMode(config.screenWidth, config.screenHeight))
        Display.create()
        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glOrtho(0.0, config.screenWidth.toDouble(), config.screenHeight.toDouble(), 0.0, -1.0, 1.0)
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        LwjglSimpleRenderer.g = Graphics(config.screenWidth,config.screenHeight)
    }

    override fun renderFrame(provider: GridProvider, view: View) {
        val delta = tracker.syncFrames()
        val widthCells = config.screenWidth / view.scale
        val heightCells = config.screenHeight / view.scale
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        while (cellRenderers.hasNextRenderer()) {
            cellRenderers.useNextRenderer(delta, view)
            for (x in view.start.x..(view.start.x + widthCells)) {
                for (y in view.start.y..(view.start.y + heightCells)) {
                    val cell = provider.getCell(x, y)
                    val info = CellRenderInfo(view.isSelected(x, y))
                    cellRenderers.renderCell(
                        cell,
                        (x.toFloat() - view.start.x) * view.scale,
                        (y.toFloat() - view.start.y) * view.scale,
                        view.scale,
                        info
                    )
                }
            }
        }
        for (x in view.start.x..(view.start.x + widthCells)) {
            for (y in view.start.y..(view.start.y + heightCells)) {
                val drawables = provider.getCellDrawables(x, y)
                val info = CellRenderInfo(view.isSelected(x, y))
                val point = Point(
                    ((x.toFloat() - view.start.x) * view.scale).toInt(),
                    ((y.toFloat() - view.start.y) * view.scale).toInt()
                )
                drawables.forEach { it.render(view.scale, point, info) }
            }
        }

        cellRenderers.reset(true)
        recorder.recordStat(RateStatistic("Frame Drawn",1))
        GL11.glEnd()
        Display.update()
    }

}