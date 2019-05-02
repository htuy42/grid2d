package com.htuy.gridworld.actors

import com.htuy.Point
import com.htuy.griddraw.Drawable
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.locations.CellAddress
import java.util.*

interface Character : GridWorldActor, Drawable{
    var locationInBlock : Point
}

abstract class AbstractCharacter(override val id: UUID = UUID.randomUUID()) : Character{

    fun getOwnLocation(block : GridWorldBlock) : CellAddress{
        return CellAddress(block.ownLocation,locationInBlock)
    }
    override fun equals(other: Any?): Boolean{
        if(other !is Character){
            return false
        }
        return other.id == id
    }
    override fun hashCode(): Int{
        return id.hashCode()
    }
}