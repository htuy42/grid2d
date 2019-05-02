package com.htuy.input

import com.htuy.event.GridEvent
import com.htuy.input.inputsuites.UICallback
import com.htuy.input.view.View
import com.htuy.input.view.ViewManager
import java.util.concurrent.BlockingQueue

/**
 * Encapsulates all of the user input logic
 */
interface UIDriver{

    /**
     * Get the stream of user input generated grid events
     */
    fun getEventStream() : BlockingQueue<GridEvent>

    /**
     * Handle a single frame of input. Triggers polling for input, and should be called once every
     * render frame
     */
    fun handleFrame()

    /**
     * Perform basic setup of the manager
     */
    fun initialize()

    fun getView() : View

    //////////////// PACKAGE PRIVATE!!! NOT TO BE USED OUTSIDE com.htuy.input ///////////////

    /**
     * PACKAGE PRIVATE
     */
    // Add a new ui callback to the driver
    fun registerNewUiCallback(callback : UICallback)

    /**
     * PACKAGE PRIVATE
     */
    //Get the view manager tracking the users screen view. Really should be only
    fun getViewManger() : ViewManager

    /**
     * PACKAGE PRIVATE
     */
    // Inject a grid event into the event stream
    fun addGridEvent(event : GridEvent)

    fun kill()
}