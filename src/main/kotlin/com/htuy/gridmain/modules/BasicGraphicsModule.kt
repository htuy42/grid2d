package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.htuy.griddraw.renderers.cell.CellRenderers
import com.htuy.griddraw.renderers.cell.implementations.material.BasicMaterialColorMapper
import com.htuy.griddraw.renderers.cell.implementations.material.MaterialColorMapper
import com.htuy.griddraw.renderers.cell.suites.MaterialCellsBorderSuite
import com.htuy.griddraw.renderers.cell.suites.RenderSuite

class BasicGraphicsModule : KotlinModule() {


    override fun configure() {
        bind<RenderSuite>().to<MaterialCellsBorderSuite>()
        bind<MaterialColorMapper>().to<BasicMaterialColorMapper>()
    }

}