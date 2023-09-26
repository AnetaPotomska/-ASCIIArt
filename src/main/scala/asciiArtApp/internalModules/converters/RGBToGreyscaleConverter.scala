package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import externalModules.converters.Converter


class RGBToGreyscaleConverter(calculateGreyValueFromRGB: RGBPixel => Int) extends Converter[RGBImage, GreyscaleImage] {
  override def convert(item: RGBImage): Option[GreyscaleImage] = {
    val height = item.getHeight
    val width = item.getWidth
    if (height <= 0 || width <= 0) {
      return None
    }
    val array = Array.ofDim[GreyscalePixel](height, width)
    val grid = GreyscaleGrid(array)
    val image = GreyscaleImage(grid)
    for((h, w) <- item) {
      val oldPixel = item.getItemOnPos(h, w)
      val newGreyValue = calculateGreyValueFromRGB(oldPixel)
      val newPixel = GreyscalePixel(newGreyValue)
      image.setItemOnPos(h, w, newPixel)
    }
    Some(image)
  }
}
