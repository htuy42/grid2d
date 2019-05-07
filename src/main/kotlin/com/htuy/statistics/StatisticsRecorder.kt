package com.htuy.statistics

interface StatisticsRecorder{
    fun recordStat(stat : Statistic)

    fun statReport()

}