package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipYAxisFilter extends FlipFilter {
  override def filter(item: GreyscaleImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[GreyscalePixel](height, width)
    val halfWidth = width / 2
    for (h <- 0 until height) {
      for (w <- 0 until halfWidth) {
        grid(h)(w) = item.getItemOnPos(h, width - w - 1)
        grid(h)(width - w - 1) = item.getItemOnPos(h, w)
      }
    }
    GreyscaleImage(GreyscaleGrid(grid))
  }
}
