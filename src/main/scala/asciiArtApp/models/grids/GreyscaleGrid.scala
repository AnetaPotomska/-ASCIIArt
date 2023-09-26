package asciiArtApp.models.grids

import asciiArtApp.models.pixels.GreyscalePixel
case class GreyscaleGrid(private val items: Array[Array[GreyscalePixel]]) extends AbstractGrid[GreyscalePixel](items) {

}
