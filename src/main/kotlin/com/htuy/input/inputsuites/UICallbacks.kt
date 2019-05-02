package com.htuy.input.inputsuites

import com.htuy.Point
import com.htuy.input.MouseButton
import com.htuy.input.UIDriver
import java.lang.IllegalArgumentException

/** any UI callback at all is one of these
 */
interface UICallback

interface asd : UICallback{

}

interface KeyCallback : UICallback

/** a callback triggered by a given key */
interface SingleKeyCallback : KeyCallback {
    /** The key to trigger on */
    val key : Int
    /** What to do */
    fun execute(driver : UIDriver)
}

data class BasicKeyCallback(override val key : Int, val callback: (UIDriver) -> Unit) :
    SingleKeyCallback {
    override fun execute(driver: UIDriver) {
        callback(driver)
    }
}

interface FallbackKeyCallback : KeyCallback{
    fun execute(driver : UIDriver, key : Int)
}

data class BasicFallbackKeyCallback(val callback : (UIDriver, Int) -> Unit): FallbackKeyCallback{
    override fun execute(driver: UIDriver, key: Int) {
        callback(driver,key)
    }
}

/**
 * A callback triggered by a given Mouse action
 */
interface MouseCallback : UICallback {
    /**
     * The mouse button that triggers this callback (ie left or right click)
     */
    val button : MouseButton
}

/**
 * A callback triggered by a click and drag
 */
interface DragCallback : MouseCallback {
    /**
     * Run the callback. Start and end are where the drag started and ended, guaranteed to be different
     * (otherwise a click callback is triggered)
     */
    fun execute(driver : UIDriver, start : Point, end : Point)
}

/**
 * A callback triggered by a click
 */
interface ClickCallback : MouseCallback {
    /**
     * Run the callback. at is the location the click occurred
     */
    fun execute(driver : UIDriver, at : Point)
}

data class BasicDragCallback(override val button: MouseButton, val callback : (UIDriver, Point, Point) -> Unit) :
    DragCallback {
    override fun execute(driver: UIDriver, start: Point, end: Point) {
        callback(driver,start,end)
    }
}

data class BasicClickCallback(override val button: MouseButton, val callback : (UIDriver, Point) -> Unit) :
    ClickCallback {
    override fun execute(driver: UIDriver, at: Point) {
        callback(driver,at)
    }
}

/**
 * Holds onto a bunch of callbacks and handles running them when appropriate
 */
class UICallbacks(private val callbacks : List<UICallback>){

    private val keyMapping = HashMap<Int, SingleKeyCallback>()
    private val dragMapping = HashMap<MouseButton, DragCallback>()
    private val clickMapping = HashMap<MouseButton, ClickCallback>()
    private var fallbackKeyCallback : FallbackKeyCallback? = null

    init{
        for(callback in callbacks){
           registerNewCallback(callback)
        }
    }

    /**
     * A key callback has has been detected by the given driver
     */
    fun keyCallback(key : Int, driver : UIDriver){
        keyMapping[key]?.execute(driver) ?: fallbackKeyCallback?.execute(driver,key)
    }

    /**
     * A click callback has been detected by the given driver
     */
    fun clickCallback(button : MouseButton, driver : UIDriver, at : Point){
        clickMapping[button]?.execute(driver,at)
    }

    /**
     * A drag callback has been detected by the given driver
     */
    fun dragCallback(button : MouseButton, driver : UIDriver, start : Point, end : Point){
        dragMapping[button]?.execute(driver,start,end)
    }

    /**
     * Add a new callback to the callback holder
     */
    fun registerNewCallback(callback : UICallback){
        when (callback) {
            is SingleKeyCallback -> keyMapping[callback.key] = callback
            is DragCallback -> dragMapping[callback.button] = callback
            is ClickCallback -> clickMapping[callback.button] = callback
            is FallbackKeyCallback -> fallbackKeyCallback = callback
            else -> throw IllegalArgumentException("Unrecognized callback type :(")
        }
    }

    /**
     * Add all callbacks held by the given callback holder to our currently registered collection,
     * and return the resulting holder. The passed object is not modified, and this is returned
     */
    fun with(callbackCollection : UICallbacks) : UICallbacks {
        for(callback in callbackCollection.callbacks){
            registerNewCallback(callback)
        }
        return this
    }

}