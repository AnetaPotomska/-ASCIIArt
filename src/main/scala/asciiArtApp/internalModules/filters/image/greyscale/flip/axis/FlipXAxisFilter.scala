package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipXAxisFilter extends FlipAxisFilter {
  override def exchangePixelsManipulator(grid: Array[Array[GreyscalePixel]], greyscaleImage: GreyscaleImage, height: Int, width: Int): Unit = {
    val halfHeight = (height / 2) + 1
    for (h <- 0 until halfHeight) {
      for (w <- 0 until width) {
        grid(h)(w) = greyscaleImage.getItemOnPos(height - h - 1, w)
        grid(height - h - 1)(w) = greyscaleImage.getItemOnPos(h, w)
      }
    }
  }
}
