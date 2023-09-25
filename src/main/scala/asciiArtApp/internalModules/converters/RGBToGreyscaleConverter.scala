package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import externalModules.converters.Converter


class RGBToGreyscaleConverter(calculateGreyValueFromRGB: RGBPixel => Int) extends Converter[RGBImage, GreyscaleImage] {
  override def convert(item: RGBImage): Option[GreyscaleImage] = {
    val height = item.getHeight
    val width = item.getWidth
    if (height == 0 || width == 0) {
      return None
    }
    val grid = Array.ofDim[GreyscalePixel](height, width)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val oldPixel = item.getItemOnPos(h, w)
        val newGreyValue = calculateGreyValueFromRGB(oldPixel)
        val newPixel = GreyscalePixel(newGreyValue)
        grid(h)(w) = newPixel
      }
    }
    Some(GreyscaleImage(GreyscaleGrid(grid)))
  }
}
