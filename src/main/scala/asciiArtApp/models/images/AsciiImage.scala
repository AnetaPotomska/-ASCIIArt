package asciiArtApp.models.images

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.pixels.AsciiPixel

case class AsciiImage(private val grid: AsciiGrid) extends AbstractImage[AsciiPixel](grid) {

}
