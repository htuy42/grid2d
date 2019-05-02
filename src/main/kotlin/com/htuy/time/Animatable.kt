package com.htuy.time

interface Animatable<T>{
    fun update(deltaMs : Int)
    fun getCurrentValue() : T

}