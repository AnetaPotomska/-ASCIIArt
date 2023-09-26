package asciiArtApp.models.grids

import asciiArtApp.models.pixels.AsciiPixel

case class AsciiGrid(private val items: Array[Array[AsciiPixel]]) extends AbstractGrid[AsciiPixel](items) {

}
