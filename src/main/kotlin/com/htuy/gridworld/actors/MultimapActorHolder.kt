package com.htuy.gridworld.actors

import com.google.common.collect.HashMultimap
import com.htuy.Point
import com.htuy.Rectangle
import com.htuy.common.RegisteredSerial
import com.htuy.gridworld.BLOCK_SIDE_SIZE

@RegisteredSerial
class MultimapActorHolder : ActorHolder {

    // only our modifications and our external facing get (get all copied) need to be synchronized.


    private val forces: MutableSet<Force> = HashSet()

    private val characters: MutableSet<Character> = HashSet()

    private val all: MutableSet<GridWorldActor> = HashSet()

    // all the characters and where they are, for localized lookup
    private val locatedCharacters: HashMultimap<Point, Character> = HashMultimap.create()

    /**
     * Get all actors in the holder
     */
    override fun getAll(): Collection<GridWorldActor> {
        return all
    }

    /**
     * Get all forces in the holder
     */
    override fun getAllForces(): Collection<Force> {
        return forces
    }

    /**
     * Get all characters in the holder
     */
    override fun getAllCharacters(): Collection<Character> {
        return characters
    }

    /**
     * Add an actor to the holder
     */
    override fun addActor(actor: GridWorldActor) {
        synchronized(this) {
            if (actor is Character) {
                characters.add(actor)
                all.add(actor)
                locatedCharacters.put(actor.locationInBlock, actor)
            } else if (actor is Force) {
                forces.add(actor)
                all.add(actor)
            } else {
                throw IllegalStateException("We only know about characters and forces. Add one of them silly!")
            }
        }
    }

    /**
     * Remove an actor from the holder
     */
    override fun removeActor(actor: GridWorldActor) {
        synchronized(this) {
            all.remove(actor)
            if (actor is Character) {
                characters.remove(actor)
                locatedCharacters.remove(actor.locationInBlock, actor)
            } else if (actor is Force) {
                forces.remove(actor)
            } else {
                throw IllegalStateException("We only know about characters and forces. Remove one of them silly!")
            }
        }
    }

    /**
     * Move a character between cells
     */
    override fun moveCharacter(character: Character, oldLocation: Point, newLocation: Point) {
        synchronized(this) {
            locatedCharacters.remove(oldLocation, character)
            locatedCharacters.put(newLocation, character)
        }
    }

    /**
     * Move a character between cells by uuid
     */

    /**
     * Get all characters at the given cell
     */
    override fun getAllAtCell(cell: Point): Collection<Character> {
        return locatedCharacters.get(cell)
    }

    /**
     * Get all characters within the given radius of the given point
     */
    // Assumes manhattan distance, radius is inclusive
    override fun getAllInRadius(cell: Point, radius: Int): Collection<Character> {
        val all = ArrayList<Character>()
        for (dx in -radius..radius) {
            for (dy in -radius..radius) {
                val newPoint = cell.delta(dx, dy)
                if (newPoint.manhattanTo(cell) <= radius) {
                    all.addAll(locatedCharacters.get(newPoint))
                }
            }
        }
        return all
    }

    /**
     * Get all characters in the given rectangle
     */
    override fun getAllInRectangle(rect: Rectangle): Collection<Character> {
        return rect.getAllPoints().flatMap {
            locatedCharacters.get(it)
        }
    }

    override fun getAllAtCellCopied(cell: Point): Collection<Character> {
        synchronized(this){
            return ArrayList(getAllAtCell(cell))
        }
    }
}