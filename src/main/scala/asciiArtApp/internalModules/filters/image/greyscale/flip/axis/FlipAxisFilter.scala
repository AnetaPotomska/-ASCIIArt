package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.internalModules.filters.image.greyscale.flip.FlipFilter
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel

abstract class FlipAxisFilter extends FlipFilter {
  def exchangePixelsManipulator(grid: Array[Array[GreyscalePixel]], greyscaleImage: GreyscaleImage, height: Int, width: Int): Unit

  override def filter(item: GreyscaleImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[GreyscalePixel](height, width)
    exchangePixelsManipulator(grid, item, height, width)
    GreyscaleImage(GreyscaleGrid(grid))
  }
}