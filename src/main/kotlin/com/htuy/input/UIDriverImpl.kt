package com.htuy.input

import com.google.inject.Inject
import com.htuy.Point
import com.htuy.config.InputConfig
import com.htuy.config.MainConfig
import com.htuy.event.GridEvent
import com.htuy.gridmain.KillSwitch
import com.htuy.input.inputsuites.UICallback
import com.htuy.input.inputsuites.UICallbacks
import com.htuy.input.view.View
import com.htuy.input.view.ViewManager
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue



class UIDriverImpl @Inject constructor(
    private val config : MainConfig,
    private val inputConfig : InputConfig,
    private val callbacks : UICallbacks,
    private val manager : ViewManager,
    private val killSwitch: KillSwitch
) : UIDriver{
    override fun kill() {
        killSwitch.kill()
    }

    override fun getView(): View {
        return manager.getCurrentView()
    }

    override fun getViewManger() : ViewManager {
        return manager
    }

    // the event stream generated by key and mouse events
    val events = LinkedBlockingQueue<GridEvent>()

    val clickStart = HashMap<MouseButton, Point?>()

    override fun initialize(){
        Keyboard.enableRepeatEvents(inputConfig.repeatedKeys)
    }

    override fun handleFrame() {
        while(Mouse.next()){
            processMouseEvent()
        }
        while(Keyboard.next()){
            processKeyboardEvent()
        }
    }

    override fun getEventStream(): BlockingQueue<GridEvent> {
        return events
    }

    private fun processMouseEvent(){
        val button = MouseButton.fromLwjglButton(Mouse.getEventButton())
        if(Mouse.getEventButtonState()){
            clickStart[button] = Point(Mouse.getEventX(),Mouse.getEventY())
        } else {
            val start = clickStart[button] ?: return
            val now = Point(Mouse.getEventX(),Mouse.getEventY())
            if(now == start){
                callbacks.clickCallback(button,this,now)
            } else {
                callbacks.dragCallback(button,this,start,now)
            }
            clickStart[button] = null
        }
    }

    private fun processKeyboardEvent(){
        if(Keyboard.getEventKeyState()) {
            callbacks.keyCallback(Keyboard.getEventKey(),this)
        }
    }

    override fun registerNewUiCallback(callback : UICallback) {
        callbacks.registerNewCallback(callback)
    }

    override fun addGridEvent(event: GridEvent) {
        events.put(event)
    }
}