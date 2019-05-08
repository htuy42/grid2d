package com.htuy.gridmain

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Guice
import com.google.inject.util.Modules
import com.htuy.common.ObjectSerializer
import com.htuy.gridmain.modules.*
import com.htuy.gridmain.modules.overrides.EvolutionRoom
import com.htuy.registerSerials

/**
 * Make an injector, use it to build an engine, and run the engine
 */
fun main(args: Array<String>) {
    ObjectSerializer.registerAllTaggedClasses(true)
    registerSerials()
    val overrides = listOf<KotlinModule>(EvolutionRoom())
    val injector = Guice.createInjector(Modules.override(BasicModule(), BasicGraphicsModule(), BasicUIModule(), GridworldGridModule()).with(overrides))
    val engine = injector.getInstance(UserEngineImpl::class.java)
    engine.runLocalInteraction()
}