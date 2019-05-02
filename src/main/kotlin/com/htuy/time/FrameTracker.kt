package com.htuy.time

import com.google.inject.Inject
import com.htuy.config.MainConfig
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

class FrameTracker @Inject constructor(val config : MainConfig){

    var lastTime = System.nanoTime() / 1000000

    fun syncFrames() : Int{
        Display.sync(config.fps)
        val curTime = System.nanoTime() / 1000000
        val deltaTime = curTime - lastTime
        lastTime = curTime
        return deltaTime.toInt()
    }
}