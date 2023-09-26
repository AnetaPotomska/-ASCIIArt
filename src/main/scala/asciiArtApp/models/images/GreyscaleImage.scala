package asciiArtApp.models.images

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.pixels.GreyscalePixel

case class GreyscaleImage(private val grid: GreyscaleGrid) extends AbstractImage[GreyscalePixel](grid) {

}