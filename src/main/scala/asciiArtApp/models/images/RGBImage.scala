package asciiArtApp.models.images

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.pixels.{Pixel, RGBPixel}

case class RGBImage(grid: RGBGrid) extends Image[RGBPixel] {

}
