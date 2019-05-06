package com.htuy.cell

import com.htuy.gridworld.contents.Material
import java.io.Serializable

interface Cell : Serializable{
    var material : Material
}

// the simplest cell possible, just provides exactly the cell interface and does nothing else
data class BasicCell(override var material: Material) : Cell