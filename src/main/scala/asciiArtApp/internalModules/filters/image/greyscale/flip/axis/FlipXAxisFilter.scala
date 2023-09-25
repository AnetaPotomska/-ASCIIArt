package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

class FlipXAxisFilter extends FlipAxisFilter {
  override def exchangePixelsManipulator(newImage: GreyscaleImage, oldImage: GreyscaleImage, height: Int, width: Int): Unit = {
    val halfHeight = (height / 2) + 1
    for (h <- 0 until halfHeight) {
      for (w <- 0 until width) {
        newImage.setItemOnPos(h, w, oldImage.getItemOnPos(height - h - 1, w))
        newImage.setItemOnPos(height - h - 1, w, oldImage.getItemOnPos(h, w))
      }
    }
  }
}
