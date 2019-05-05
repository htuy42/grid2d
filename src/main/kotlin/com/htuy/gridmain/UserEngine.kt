package com.htuy.gridmain

/**
 * An engine the user can interact with the grid. When run, it should (if necessary)
 * create an underlying grid engine, and then use a rendering engine to draw it
 */
interface UserEngine{
    fun runLocalInteraction()
}