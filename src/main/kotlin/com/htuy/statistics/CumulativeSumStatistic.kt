package com.htuy.statistics

import java.lang.IllegalStateException

class CumulativeSumStatistic(override val name : String, var sum : Int = 0) : ReportNameValueStatistic{
    override fun getValue(deltaMs: Long) : Int {
        return sum
    }

    override fun combine(other: Statistic) {
        if(other !is CumulativeSumStatistic){
            throw IllegalStateException("Multiple statistics with the same name but different type. $this $other")
        } else {
            this.sum += other.sum
        }
    }

    override val isCumulative: Boolean = true
}