package com.htuy.gridmain

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