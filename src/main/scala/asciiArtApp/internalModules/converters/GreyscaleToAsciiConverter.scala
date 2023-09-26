package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage}
import asciiArtApp.models.pixels.AsciiPixel
import externalModules.converters.Converter
import externalModules.converters.intToCharByTable.IntToCharConverter

class GreyscaleToAsciiConverter(table: IntToCharConverter) extends Converter[GreyscaleImage, AsciiImage] {
  private def calculateAsciiValueFromGrey(grey: Int) : Char = {
    val toRet = table.convert(grey)
    if(toRet.isEmpty) {
      throw new Exception("Couldn't calculate ascii value from greyscale value")
    }
    toRet.get
  }

  override def convert(item: GreyscaleImage): Option[AsciiImage] = {
    val height = item.getHeight
    val width = item.getWidth
    if(height <= 0 || width <= 0) {
      return None
    }
    val array = Array.ofDim[AsciiPixel](height, width)
    val grid = AsciiGrid(array)
    val image = AsciiImage(grid)
    for((h, w) <- item) {
      val oldPixel = item.getItemOnPos(h, w)
      val newAsciiValue = calculateAsciiValueFromGrey(oldPixel.grey)
      val newPixel = AsciiPixel(newAsciiValue)
      image.setItemOnPos(h, w, newPixel)
    }
    Some(image)
  }
}
