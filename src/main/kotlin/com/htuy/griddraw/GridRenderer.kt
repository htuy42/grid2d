package com.htuy.griddraw

import com.htuy.gridprovider.GridProvider
import com.htuy.input.view.View

interface GridRenderer{
    fun renderFrame(provider : GridProvider, view : View)
    fun initialize()
}