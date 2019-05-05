package com.htuy.gridmain

/**
 * Tells us whether or not the system is dead yet. When it is killed, it marks itself
 * as dead, so systems can have a little bit of time to shut down, and then
 * it system.exits.
 */
class KillSwitch{

    private var isDead : Boolean = false

    fun kill(status : Int = 0){
        isDead = true
        Thread.sleep(2000)
        System.exit(status)
    }

    fun getIsDead() : Boolean{
        return isDead
    }

}