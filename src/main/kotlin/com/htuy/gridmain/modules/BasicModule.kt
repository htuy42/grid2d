package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.htuy.config.MainConfig
import com.htuy.griddraw.GridRenderer
import com.htuy.griddraw.LwjglSimpleRenderer
import com.htuy.gridprovider.GridProvider
import com.htuy.gridprovider.GridProviderImpl
import com.htuy.input.UIDriver
import com.htuy.input.UIDriverImpl

open class BasicModule : KotlinModule(){
    open val config = MainConfig()
    override fun configure() {
        bind<GridProvider>().to<GridProviderImpl>()
        bind<MainConfig>().toInstance(config)
        bind<GridRenderer>().to<LwjglSimpleRenderer>()
        bind<UIDriver>().to<UIDriverImpl>()
    }
}