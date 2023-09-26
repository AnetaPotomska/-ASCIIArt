package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.models.images.GreyscaleImage

class FlipXAxisFilter extends FlipAxisFilter {
  // go to half of image (from up to down) and in the meantime swap pixels (on whole width) for pixels on the other side of the image (upper part for bottom part)
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
