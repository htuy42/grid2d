package com.htuy.statistics

import com.google.inject.Inject
import com.htuy.config.MainConfig
import com.htuy.gridmain.KillSwitch
import kotlin.concurrent.thread

interface StatisticsReporter {
    fun beginReporting()
}

class StatisticsReporterImpl @Inject constructor(
    val recorder: StatisticsRecorder,
    val killSwitch: KillSwitch,
    val config: MainConfig
) : StatisticsReporter {
    /**
     * Will never return. Should be called from another thread if other things should also happen
     */
    override fun beginReporting() {
        while (!killSwitch.getIsDead()) {
            Thread.sleep(config.reportFrequency)
            recorder.statReport()
        }
    }
}