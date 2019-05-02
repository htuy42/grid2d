package com.htuy.gridworld.actors

import com.htuy.Point
import com.htuy.Rectangle
import java.io.Serializable
import java.util.*

interface ActorHolder : Serializable {
    /**
     * Get all actors in the holder
     */
    fun getAll() : Collection<GridWorldActor>

    /**
     * Get all forces in the holder
     */
    fun getAllForces() : Collection<Force>

    /**
     * Get all characters in the holder
     */
    fun getAllCharacters() : Collection<Character>

    /**
     * Add an actor to the holder
     */
    fun addActor(actor : GridWorldActor)

    /**
     * Remove an actor from the holder
     */
    fun removeActor(actor : GridWorldActor)

    /**
     * Move a character between cells
     */
    fun moveCharacter(character : Character, oldLocation : Point, newLocation : Point)


    /**
     * Get all characters at the given cell
     */
    fun getAllAtCell(cell : Point) : Collection<Character>

    /**
     * Get all characters within the given radius of the given point
     */
    // Assumes manhattan distance, radius is inclusive
    fun getAllInRadius(cell : Point, radius : Int) : Collection<Character>

    /**
     * Get all characters in the given rectangle
     */
    fun getAllInRectangle(rect : Rectangle) : Collection<Character>

    fun getAllAtCellCopied(cell : Point) : Collection<Character>
}