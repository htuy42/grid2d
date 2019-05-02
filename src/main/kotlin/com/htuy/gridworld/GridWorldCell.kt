package com.htuy.gridworld

import com.htuy.cell.Cell
import com.htuy.gridworld.contents.Material
import com.htuy.gridworld.events.GridWorldEvent

class GridWorldCell(override var material: Material) : Cell {}