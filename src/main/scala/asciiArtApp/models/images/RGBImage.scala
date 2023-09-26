package asciiArtApp.models.images

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.pixels.RGBPixel

case class RGBImage(private val grid: RGBGrid) extends AbstractImage[RGBPixel](grid) {

}
