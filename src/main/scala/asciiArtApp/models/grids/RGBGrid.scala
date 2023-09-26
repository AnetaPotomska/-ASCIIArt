package asciiArtApp.models.grids

import asciiArtApp.models.pixels.RGBPixel

case class RGBGrid(private val items: Array[Array[RGBPixel]]) extends AbstractGrid[RGBPixel](items) {

}
