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
    if(height == 0 || width == 0) {
      return None
    }
    val grid = Array.ofDim[AsciiPixel](height, width)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val oldPixel = item.getItemOnPos(h, w)
        val newAsciiValue = calculateAsciiValueFromGrey(oldPixel.grey)
        val newPixel = AsciiPixel(newAsciiValue)
        grid(h)(w) = newPixel
      }
    }
    val x = AsciiGrid(grid)

    Some(AsciiImage(AsciiGrid(grid)))
  }
}
