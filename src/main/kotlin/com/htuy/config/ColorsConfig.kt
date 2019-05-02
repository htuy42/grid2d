package com.htuy.config

import com.htuy.util.Color

val SOFT_YELLOW = Color(255,242,83)
val SOFT_GREEN_GREY = Color(134,153,111)
val DARK_LIME_GREEN = Color(134,204,46)
val SOFT_PURPLE_BLUE = Color(149,147,255)
val PINK_BLOOD_RED = Color(134,26,54)

data class ColorsConfig(val highlightColor : Color = SOFT_YELLOW,
                        val backgroundColor : Color = SOFT_GREEN_GREY,
                        val baseColor : Color = DARK_LIME_GREEN,
                        val waterColor : Color = SOFT_PURPLE_BLUE,
                        val highlightColor2 : Color = PINK_BLOOD_RED)