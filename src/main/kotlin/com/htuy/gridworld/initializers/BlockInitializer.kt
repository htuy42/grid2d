package com.htuy.gridworld.initializers

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.locations.HyperPoint
import com.htuy.gridworld.locations.LocationFetcher

/**
 * Knows how to make new blocks given a location fetcher to add to a grid world
 */
interface BlockInitializer{
    /**
     * Create a new block at the given location in the gridworld represented by the given fetcher.
     * SHOULD NOT call fetcher.getBlockByHyperpoint or fetcher.getCellByAddress, since they may call back to this
     * Instead, use the nullable getBlockByHyperpointInit
     */
    fun makeNewBlock(fetcher: LocationFetcher, location : HyperPoint) : GridWorldBlock
}