package com.htuy.gridworld.events.types

import com.htuy.gridworld.GridWorldBlock
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.events.GridWorldEvent
import com.htuy.gridworld.locations.CellAddress
import com.sbf.eventengine.eventobjects.StateMachine

/**
 * Change the given cells material
 */
class ChangeCellMaterialEvent(
    override val fromCell: CellAddress,
    override val toCell: CellAddress,
    val material: Material
) : AbstractCellTargetedEvent() {
    override fun applyNoRes(to: StateMachine<GridWorldBlock>){
        to.machine.getCell(toCell).material = material
    }
}