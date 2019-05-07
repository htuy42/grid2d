package com.htuy.statistics

import java.util.concurrent.ConcurrentHashMap

class StatisticsRecorderImpl : StatisticsRecorder {
    var lastReportTime = System.currentTimeMillis()
    private val stats = HashMap<String, Statistic>()
    override fun recordStat(stat: Statistic) {
        synchronized(stats) {
            if (stat.name !in stats) {
                stats.put(stat.name, stat)
            } else {
                stats[stat.name]!!.combine(stat)
            }
        }
    }
    override fun statReport() {
        val elapsed = System.currentTimeMillis() - lastReportTime
        synchronized(stats) {
            for (elt in stats.values) {
                elt.report(elapsed)
            }
            val toKeep = stats.values.filter{it.isCumulative}
            stats.clear()
            for(elt in toKeep){
                stats.put(elt.name,elt)
            }
        }
        lastReportTime = System.currentTimeMillis()
    }
}