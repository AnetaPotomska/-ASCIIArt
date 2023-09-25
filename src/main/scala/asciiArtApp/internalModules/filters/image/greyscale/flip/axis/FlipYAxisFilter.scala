package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipYAxisFilter extends FlipAxisFilter {
  override def exchangePixelsManipulator(newImage: GreyscaleImage, oldImage: GreyscaleImage, height: Int, width: Int): Unit = {
    val halfWidth = (width / 2) + 1
    for (h <- 0 until height) {
      for (w <- 0 until halfWidth) {
        newImage.setItemOnPos(h, w, oldImage.getItemOnPos(h, width - w - 1))
        newImage.setItemOnPos(h, width - w - 1, oldImage.getItemOnPos(h, w))
      }
    }
  }
}
