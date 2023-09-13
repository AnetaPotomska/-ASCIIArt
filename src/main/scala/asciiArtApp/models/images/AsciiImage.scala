package asciiArtApp.models.images

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.pixels.AsciiPixel

case class AsciiImage(grid: AsciiGrid) extends Image[AsciiPixel] {

}
