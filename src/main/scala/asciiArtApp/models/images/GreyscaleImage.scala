package asciiArtApp.models.images

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.pixels.GreyscalePixel

case class GreyscaleImage(grid: GreyscaleGrid) extends Image[GreyscalePixel] {

}