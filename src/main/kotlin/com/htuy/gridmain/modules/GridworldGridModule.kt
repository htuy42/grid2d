package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Singleton
import com.htuy.gridprovider.EventStreamHandler
import com.htuy.gridprovider.cellproviders.CellProvider
import com.htuy.gridprovider.initializer.SimpleCellInitializer
import com.htuy.gridworld.GridWorld
import com.htuy.gridworld.cache.CacheGridworldProvider
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.events.user.TestUserEventStreamProcessor
import com.htuy.gridworld.events.user.UserEventStreamProcessor
import com.htuy.gridworld.initializers.BlockInitializer
import com.htuy.gridworld.initializers.CellInitializingBlockInitializer
import com.htuy.gridworld.initializers.GridWorldCyclingCellInitializer
import com.htuy.gridworld.initializers.GridWorldGridCellInitializer
import com.htuy.gridworld.local.FakeRemote
import com.htuy.gridworld.local.LocalGridWorld

open class GridworldGridModule : KotlinModule(){
    override fun configure() {
        val initializer = GridWorldGridCellInitializer(Material.FIRE,Material.EARTH,Material.WATER)
        bind<SimpleCellInitializer>().toInstance(initializer)
        bind<BlockInitializer>().to<CellInitializingBlockInitializer>().`in`(Singleton::class.java)
        bind<FakeRemote>().`in`<Singleton>()
        bind<LocalGridWorld>().`in`<Singleton>()
        bind<GridWorld>().to<LocalGridWorld>().`in`(Singleton::class.java)
        bind<CellProvider>().to<LocalGridWorld>().`in`(Singleton::class.java)
        bind<EventStreamHandler>().to<LocalGridWorld>().`in`(Singleton::class.java)
        bind<UserEventStreamProcessor>().to<TestUserEventStreamProcessor>().`in`(Singleton::class.java)
    }
}