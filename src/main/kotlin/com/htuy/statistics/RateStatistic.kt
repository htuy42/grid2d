package com.htuy.statistics

import java.lang.IllegalStateException

class RateStatistic(override val name: String, var sum : Int) : ReportNameValueStatistic{
    override fun getValue(deltaMs: Long): Any {
        return sum.toDouble() * 1000.0 / deltaMs
    }

    override fun combine(other: Statistic) {
        if(other !is RateStatistic){
            throw IllegalStateException("Tried to combine two stats of different type. $this $other")
        } else {
            sum += other.sum
        }
    }

    override val isCumulative: Boolean = false
}