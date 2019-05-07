package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Singleton
import com.htuy.griddraw.renderers.cell.CellRenderers
import com.htuy.griddraw.renderers.cell.implementations.material.BasicMaterialColorMapper
import com.htuy.griddraw.renderers.cell.implementations.material.MaterialColorMapper
import com.htuy.griddraw.renderers.cell.suites.MaterialCellsBorderSuite
import com.htuy.griddraw.renderers.cell.suites.RenderSuite

/**
 * Basic collection of graphics controls
 */
class BasicGraphicsModule : KotlinModule() {
    override fun configure() {
        bind<RenderSuite>().to<MaterialCellsBorderSuite>().`in`(Singleton::class.java)
        bind<MaterialColorMapper>().to<BasicMaterialColorMapper>().`in`(Singleton::class.java)
    }
}