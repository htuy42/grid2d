package com.htuy.gridworld.events.user

import com.htuy.event.GridEvent
import com.htuy.gridworld.events.GridWorldEvent

interface UserEventStreamProcessor{
    fun process(event : GridEvent) : GridWorldEvent?
}