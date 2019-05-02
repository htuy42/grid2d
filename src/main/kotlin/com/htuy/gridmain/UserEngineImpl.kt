package com.htuy.gridmain

import com.htuy.griddraw.GridRenderer
import com.htuy.gridprovider.GridProvider
import com.htuy.input.UIDriver
import javax.inject.Inject
import kotlin.concurrent.thread

class UserEngineImpl @Inject constructor(val provider : GridProvider, val renderer: GridRenderer, val driver : UIDriver, val killSwitch: KillSwitch)  : UserEngine{
    override fun runLocalInteraction() {
        thread{
            provider.start()
        }
        thread{
            renderer.initialize()
            driver.initialize()
            provider.provideEventStream(driver.getEventStream())
            while(!killSwitch.getIsDead()){
                renderer.renderFrame(provider, driver.getView())
                driver.handleFrame()
            }
        }.join()
        provider.stop()
    }

}