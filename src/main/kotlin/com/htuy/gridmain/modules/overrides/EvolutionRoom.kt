package com.htuy.gridmain.modules.overrides

import com.authzee.kotlinguice4.KotlinModule
import com.htuy.config.MainConfig

class EvolutionRoom: KotlinModule(){
    override fun configure() {
        bind<MainConfig>().toInstance(MainConfig(defaultScale = 10))
    }
}