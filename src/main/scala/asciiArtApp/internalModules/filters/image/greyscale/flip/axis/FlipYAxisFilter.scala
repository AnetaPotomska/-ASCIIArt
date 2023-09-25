package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipYAxisFilter extends FlipAxisFilter {
  override def exchangePixelsManipulator(grid: Array[Array[GreyscalePixel]], greyscaleImage: GreyscaleImage, height: Int, width: Int): Unit = {
    val halfWidth = (width / 2) + 1
    for (h <- 0 until height) {
      for (w <- 0 until halfWidth) {
        grid(h)(w) = greyscaleImage.getItemOnPos(h, width - w - 1)
        grid(h)(width - w - 1) = greyscaleImage.getItemOnPos(h, w)
      }
    }
  }
}
