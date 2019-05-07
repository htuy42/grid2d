package com.htuy.config

data class MainConfig(
    val screenHeight: Int = 1300,
    val screenWidth: Int = 2500,
    val screenTitle: String = "Grid World",
    val defaultScale: Int = 50,
    val fps: Int = 60,
    val reportFrequency : Long = 5000
)