package com.htuy.gridmain

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Guice
import com.google.inject.util.Modules
import com.htuy.gridmain.modules.*

/**
 * Make an injector, use it to build an engine, and run the engine
 */
fun main(args: Array<String>) {
    val overrides = listOf<KotlinModule>()
    val injector = Guice.createInjector(Modules.override(BasicModule(), BasicGraphicsModule(), BasicUIModule(), GridworldGridModule()).with(overrides))
    val engine = injector.getInstance(UserEngineImpl::class.java)
    engine.runLocalInteraction()
}