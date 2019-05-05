package com.htuy.griddraw.renderers.cell.implementations.material

import com.google.inject.Inject
import com.htuy.config.ColorsConfig
import com.htuy.gridworld.contents.Material
import com.htuy.util.Color

/**
 * Maps from materials to rendering colors
 */
interface MaterialColorMapper{
    fun materialToColor(material : Material) : Color
}


/**
 * The most basic implementation, just hand coded color mappings
 */
class BasicMaterialColorMapper @Inject constructor(val config : ColorsConfig) :
    MaterialColorMapper {
    override fun materialToColor(material: Material): Color {
        return when(material){
            Material.WATER -> config.waterColor
            Material.EARTH -> config.baseColor
            Material.FIRE -> config.highlightColor2
        }
    }

}