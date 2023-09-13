package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipYAxisFilter extends FlipFilter {
  override def filter(item: GreyscaleImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[GreyscalePixel](width, height)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val newRow = h
        val newCol = width - 1 - w
        grid(newRow)(newCol) = item.getItemOnPos(newRow, newCol)
      }
    }
    GreyscaleImage(GreyscaleGrid(grid))
  }
}
