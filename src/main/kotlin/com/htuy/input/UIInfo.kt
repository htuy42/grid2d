package com.htuy.input

import com.htuy.Point
import com.htuy.gridworld.BLOCK_SIDE_SIZE
import com.htuy.gridworld.locations.CellAddress
import com.htuy.gridworld.locations.HyperPoint
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

enum class Direction {

    UP, DOWN, LEFT, RIGHT;

    /**
     * Return the point corresponding to moving in this direction from the given point
     */
    fun applyTo(prev: Point): Point {
        return when (this) {
            UP -> prev.delta(0, -1)
            DOWN -> prev.delta(0, 1)
            LEFT -> prev.delta(-1, 0)
            RIGHT -> prev.delta(1, 0)
        }
    }

    /**
     * Return the cell address corresponding to moving in this direction from the given cell address
     */
    fun applyTo(prev: CellAddress): CellAddress {
        return when (this) {
            UP -> if (prev.cellLocation.y == 0) {
                CellAddress(
                    HyperPoint(
                        prev.blockAddress.scale,
                        Point(prev.blockAddress.internalPoint.x, prev.blockAddress.internalPoint.y - 1)
                    ), Point(
                        prev.cellLocation.x,
                        BLOCK_SIDE_SIZE - 1
                    )
                )
            } else {
                CellAddress(prev.blockAddress, this.applyTo(prev.cellLocation))
            }
            DOWN -> if (prev.cellLocation.y == BLOCK_SIDE_SIZE - 1) {
                CellAddress(
                    HyperPoint(
                        prev.blockAddress.scale,
                        Point(prev.blockAddress.internalPoint.x, prev.blockAddress.internalPoint.y + 1)
                    ),
                    Point(prev.cellLocation.x, 0)
                )
            } else {
                CellAddress(prev.blockAddress, this.applyTo(prev.cellLocation))
            }
            LEFT -> if (prev.cellLocation.x == 0) {
                CellAddress(
                    HyperPoint(
                        prev.blockAddress.scale,
                        Point(prev.blockAddress.internalPoint.x - 1, prev.blockAddress.internalPoint.y)
                    ), Point(
                        BLOCK_SIDE_SIZE - 1,
                        prev.cellLocation.y
                    )
                )
            } else {
                CellAddress(prev.blockAddress, this.applyTo(prev.cellLocation))
            }
            RIGHT -> if (prev.cellLocation.x == BLOCK_SIDE_SIZE - 1) {
                CellAddress(
                    HyperPoint(
                        prev.blockAddress.scale,
                        Point(prev.blockAddress.internalPoint.x + 1, prev.blockAddress.internalPoint.y)
                    ),
                    Point(0, prev.cellLocation.y)
                )
            } else {
                CellAddress(prev.blockAddress, this.applyTo(prev.cellLocation))
            }
        }
    }

    companion object {
        val rand: Random by lazy { Random() }
        /**
         * Get a random direction
         */
        fun random(): Direction {
            val rand = rand.nextInt(4)
            return when (rand) {
                0 -> UP
                1 -> DOWN
                2 -> LEFT
                3 -> RIGHT
                else -> {
                    throw IllegalStateException("Impossible!")
                }
            }
        }
    }

}

/**
 * the various buttons on a mouse we might press
 */
enum class MouseButton {
    LEFT, MIDDLE, RIGHT, NONE;

    companion object {
        /**
         * convert lwjgl's button representation to a MouseButton
         */
        fun fromLwjglButton(lwjglButton: Int): MouseButton {
            return when (lwjglButton) {
                -1 -> NONE
                0 -> LEFT
                1 -> RIGHT
                2 -> MIDDLE
                else -> throw IllegalArgumentException("Not a recognized mouse button!")
            }
        }
    }


}