package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel

import scala.util.Random

class RandomImageGenerator(random: Random, minImageHeight: Int, maxImageHeight: Int, minImageWidth: Int, maxImageWidth: Int) extends RandomImageLoader {
  private def checkBounds(min: Int, max: Int): Boolean = {
    min > 0 && max > 0 && min <= max
  }
  override def load(): Option[RGBImage] = {
    // check bounds for height and width
    if(!checkBounds(minImageHeight, maxImageHeight) || !checkBounds(minImageWidth, maxImageWidth)) {
      return None
    }
    // generate random numbers for height and width
    val height = random.between(minImageHeight, maxImageHeight)
    val width = random.between(minImageWidth, maxImageWidth)

    // init
    val array = Array.ofDim[RGBPixel](height, width)
    val grid = RGBGrid(array)
    val image = RGBImage(grid)

    // go through every pixel and assign random rgb values to it
    for((h, w) <- image) {
      val redValue = random.between(0, 255)
      val greenValue = random.between(0, 255)
      val blueValue = random.between(0, 255)
      val pixel = RGBPixel(redValue, greenValue, blueValue)
      image.setItemOnPos(h, w, pixel)
    }
    Some(image)
  }
}
