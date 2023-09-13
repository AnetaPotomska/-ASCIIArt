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
    val halfHeight = height / 2
    for (h <- 0 until halfHeight) {
      for (w <- 0 until width) {
        grid(h)(w) = item.getItemOnPos(height - h - 1, w)
        grid(height - h - 1)(w) = item.getItemOnPos(h, w)
      }
    }
    GreyscaleImage(GreyscaleGrid(grid))
  }
}
