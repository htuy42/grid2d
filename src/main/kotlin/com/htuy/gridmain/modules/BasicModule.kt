package com.htuy.gridmain.modules

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Singleton
import com.htuy.config.MainConfig
import com.htuy.griddraw.GridRenderer
import com.htuy.griddraw.LwjglSimpleRenderer
import com.htuy.gridmain.KillSwitch
import com.htuy.gridprovider.GridProvider
import com.htuy.gridprovider.GridProviderImpl
import com.htuy.input.UIDriver
import com.htuy.input.UIDriverImpl
import com.htuy.statistics.StatisticsRecorder
import com.htuy.statistics.StatisticsRecorderImpl
import com.htuy.statistics.StatisticsReporter
import com.htuy.statistics.StatisticsReporterImpl

open class BasicModule : KotlinModule(){
    open val config = MainConfig()
    override fun configure() {
        bind<GridProvider>().to<GridProviderImpl>().`in`(Singleton::class.java)
        bind<MainConfig>().toInstance(config)
        bind<GridRenderer>().to<LwjglSimpleRenderer>().`in`(Singleton::class.java)
        bind<UIDriver>().to<UIDriverImpl>().`in`(Singleton::class.java)
        bind<StatisticsRecorder>().to<StatisticsRecorderImpl>().`in`(Singleton::class.java)
        bind<StatisticsReporter>().to<StatisticsReporterImpl>().`in`(Singleton::class.java)
        bind<KillSwitch>().`in`(Singleton::class.java)
    }
}