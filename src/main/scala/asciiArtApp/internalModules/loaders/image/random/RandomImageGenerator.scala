package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel

import scala.util.Random

class RandomImageGenerator() extends RandomImageLoader {
  override def load(): RGBImage = {
    val randomizer = Random
    val height = randomizer.between(minImageHeight, maxImageHeight)
    val width = randomizer.between(minImageWidth, maxImageWidth)
    val grid = Array.ofDim[RGBPixel](height, width)
    for(h <- 0 until height) {
      for (w <- 0 until width) {
        val redValue = randomizer.between(minPixelValue, maxPixelValue)
        val greenValue = randomizer.between(minPixelValue, maxPixelValue)
        val blueValue = randomizer.between(minPixelValue, maxPixelValue)
        val pixel = RGBPixel(redValue, greenValue, blueValue)
        grid(h)(w) = pixel
      }
    }
    RGBImage(RGBGrid(grid))
  }
}
