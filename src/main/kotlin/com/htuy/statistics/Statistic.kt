package com.htuy.statistics

interface Statistic{
    val name : String

    fun combine(other : Statistic)

    fun report(deltaMs : Long)

    val isCumulative : Boolean

}