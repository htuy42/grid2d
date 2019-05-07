package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Singleton
import com.htuy.config.InputConfig
import com.htuy.input.inputsuites.BASIC_CLICK_DRAG_SELECTION_SUITE
import com.htuy.input.inputsuites.BASIC_UI_CONTROL_SUITE
import com.htuy.input.inputsuites.UICallbacks
import com.htuy.input.view.ViewManager
import com.htuy.input.view.ViewManagerImpl

open class BasicUIModule : KotlinModule(){

    open val callbacks = UICallbacks(listOf())
        .with(BASIC_UI_CONTROL_SUITE).with(BASIC_CLICK_DRAG_SELECTION_SUITE)

    open val config = InputConfig()

    override fun configure() {
        bind<UICallbacks>().toInstance(callbacks)
        bind<ViewManager>().to<ViewManagerImpl>().`in`(Singleton::class.java)
        bind<InputConfig>().toInstance(config)
    }
}