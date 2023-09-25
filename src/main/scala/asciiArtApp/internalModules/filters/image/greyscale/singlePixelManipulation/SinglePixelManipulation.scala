package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation

import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

abstract class SinglePixelManipulation extends GreyscaleImageFilter {
  def pixelManipulator(value: Int): Int

  override def filter(item: GreyscaleImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val array = Array.ofDim[GreyscalePixel](height, width)
    val grid = GreyscaleGrid(array)
    val image = GreyscaleImage(grid)
    for ((h, w) <- item) {
      val oldPixel = item.getItemOnPos(h, w)
      val newGreyValue = pixelManipulator(oldPixel.grey)
      val newPixel = GreyscalePixel(newGreyValue)
      image.setItemOnPos(h, w, newPixel)
    }
    image
  }
}
