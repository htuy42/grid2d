package com.htuy.time

open class PulsingAnimatable(val frequency : Int, val floor : Float,val ceil : Float) : Animatable<Float>{

    private var accumulatedTime = 0
    private val distance = ceil - floor

    override fun update(deltaMs: Int) {
        accumulatedTime += deltaMs
    }

    override fun getCurrentValue(): Float {
        val phase = (accumulatedTime / frequency) % 2
        var amount : Float = (accumulatedTime % frequency).toFloat()
        if(phase == 1){
            amount = frequency - amount
        }
        amount /= frequency
        return amount * distance + floor
    }
}