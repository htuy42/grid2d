package com.htuy.gridworld.initializers

import com.google.inject.Inject
import com.htuy.Point
import com.htuy.gridprovider.initializer.CellInitializer
import com.htuy.gridprovider.initializer.SimpleCellInitializer
import com.htuy.gridworld.BLOCK_SIDE_SIZE
import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.GridWorldCell
import com.htuy.gridworld.locations.HyperPoint
import com.htuy.gridworld.locations.LocationFetcher

/**
 * Initializes blocks by using the bound cell initializer and just creating a block with those cells.
 * Requires that the CellInitializer be a SimpleCellInitializer
 */
class CellInitializingBlockInitializer @Inject constructor(val cellInit: SimpleCellInitializer) : BlockInitializer {
    override fun makeNewBlock(fetcher: LocationFetcher, location: HyperPoint): GridWorldBlock {
        return GridWorldBlock(location, (0 until BLOCK_SIDE_SIZE).map {x->
            (0 until BLOCK_SIDE_SIZE).map {y->
                val point = location.innerLocationToFlatPoint(Point(x,y))
                cellInit.getNextCell(point.x,point.y) as GridWorldCell
            }
        },0)
    }
}