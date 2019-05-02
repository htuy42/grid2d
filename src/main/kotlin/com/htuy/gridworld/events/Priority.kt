package com.htuy.gridworld.events

enum class Priority{
    TRANSCENDENT,
    ;
    fun toInt() : Int{
        return when(this){
            TRANSCENDENT -> 100000;
        }
    }
}