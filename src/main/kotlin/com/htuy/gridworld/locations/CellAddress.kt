package com.htuy.gridworld.locations

import com.htuy.Point
import com.htuy.gridworld.BLOCK_SIDE_SIZE

data class CellAddress(val blockAddress: HyperPoint, val cellLocation: Point) {
    companion object {
        fun fromFlatPoint(flatPoint: Point, scale: Int = BLOCK_SIDE_SIZE) : CellAddress {
            val cellX = if(flatPoint.x >= 0){
                flatPoint.x % scale
            } else {
                // if we are negative, we can't just use normal mod
                scale -1 - flatPoint.x % scale * -1
            }
            val cellY = if(flatPoint.y >= 0){
                flatPoint.y % scale
            } else {
                scale -1 - flatPoint.y % scale * -1
            }
            return CellAddress(
                HyperPoint.fromFlatPoint(flatPoint, scale),
                Point(cellX,cellY)
            )
        }
    }


    // if we are out of bounds in our block, returns a cell address that represents our actual location.
    // Currently seems to be bugged, so in general prefer to use Direction.applyTo instead of using this.
    fun rectify() : CellAddress{
        val finBlockX = blockAddress.internalPoint.x + cellLocation.x / BLOCK_SIDE_SIZE
        val finBlockY = blockAddress.internalPoint.y + cellLocation.y / BLOCK_SIDE_SIZE
        var finCellX = cellLocation.x % BLOCK_SIDE_SIZE
        var finCellY = cellLocation.y % BLOCK_SIDE_SIZE
        if(finCellX < 0){
            finCellX += BLOCK_SIDE_SIZE
        }
        if(finCellY < 0){
            finCellY += BLOCK_SIDE_SIZE
        }
        return CellAddress(HyperPoint(blockAddress.scale,Point(finBlockX,finBlockY)),Point(finCellX,finCellY))
    }

}