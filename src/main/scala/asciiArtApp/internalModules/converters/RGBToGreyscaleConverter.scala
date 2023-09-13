package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import externalModules.converters.Converter


class RGBToGreyscaleConverter extends Converter[RGBImage, GreyscaleImage] {
  private def calculateGreyValueFromRGB(pixel: RGBPixel) : Int = {
    ((0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)).floor.toInt
  }

  override def convert(item: RGBImage): GreyscaleImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[GreyscalePixel](width, height)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val oldPixel = item.getItemOnPos(h, w)
        val newGreyValue = calculateGreyValueFromRGB(oldPixel)
        val newPixel = GreyscalePixel(newGreyValue)
        grid(w)(h) = newPixel
      }
    }
    GreyscaleImage(GreyscaleGrid(grid))
  }
}
