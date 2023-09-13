package asciiArtApp.models.grids

import asciiArtApp.models.pixels.AsciiPixel

case class AsciiGrid(items: Array[Array[AsciiPixel]]) extends Grid[AsciiPixel] {

}
