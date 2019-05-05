package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.htuy.gridprovider.EventStreamHandler
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridprovider.cellproviders.LocalArrayCellProvider
import com.htuy.gridprovider.eventstreamhandlers.CycleCellEventStreamHandler
import com.htuy.gridprovider.initializer.CellInitializer
import com.htuy.gridprovider.initializer.CyclingCellInitializer
import com.htuy.gridworld.contents.Material

/**
 * Basic collection of grid providing items
 */
open class BasicGridModule : KotlinModule(){
    override fun configure() {
        val initializer = CyclingCellInitializer(listOf(Material.FIRE, Material.WATER, Material.EARTH))
        bind<CellInitializer>().toInstance(initializer)
        bind<CellProvider>().toInstance(LocalArrayCellProvider(5000, initializer))
        bind<EventStreamHandler>().to<CycleCellEventStreamHandler>()
    }
}