package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel

import scala.util.Random

class RandomImageGenerator(random: Random, minImageHeight: Int, maxImageHeight: Int, minImageWidth: Int, maxImageWidth: Int) extends RandomImageLoader {
  private def checkBounds(min: Int, max: Int): Boolean = {
    min > 0 && max > 0 && min <= max
  }
  override def load(): RGBImage = {
    if(!checkBounds(minImageHeight, maxImageHeight) || !checkBounds(minImageWidth, maxImageWidth)) {
      throw new Exception("Bounds for random are incorrect")
    }
    val height = random.between(minImageHeight, maxImageHeight)
    val width = random.between(minImageWidth, maxImageWidth)
    val grid = Array.ofDim[RGBPixel](height, width)
    for(h <- 0 until height) {
      for (w <- 0 until width) {
        val redValue = random.between(0, 255)
        val greenValue = random.between(0, 255)
        val blueValue = random.between(0, 255)
        val pixel = RGBPixel(redValue, greenValue, blueValue)
        grid(h)(w) = pixel
      }
    }
    RGBImage(RGBGrid(grid))
  }
}
