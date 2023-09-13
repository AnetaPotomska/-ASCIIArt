package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{GreyscaleImage, Image}
import asciiArtApp.models.pixels.{GreyscalePixel, Pixel}

class FlipXAxisFilter extends FlipFilter {
  override def filter(item: GreyscaleImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[GreyscalePixel](height, width)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val newRow = height - 1 - h
        val newCol = w
        grid(newRow)(newCol) = item.getItemOnPos(newRow, newCol)
      }
    }
    GreyscaleImage(GreyscaleGrid(grid))
  }
}
