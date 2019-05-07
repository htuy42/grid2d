package com.htuy.statistics

interface ReportNameValueStatistic : Statistic{

    fun getValue(deltaMs : Long) : Any

    override fun report(deltaMs: Long) {
        println("$name: ${getValue(deltaMs)}")
    }
}