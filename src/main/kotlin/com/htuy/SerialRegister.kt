package com.htuy

import com.google.common.collect.HashMultimap
import com.htuy.common.ObjectSerializer
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.actors.ActorHolder
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import org.omg.CORBA.Object

fun registerSerials(){
    ObjectSerializer.registerClassToSerialize(HashSet::class.java)
    ObjectSerializer.registerClassToSerialize(ArrayList::class.java)
    ObjectSerializer.registerClassToSerialize(HashMultimap::class.java)
}