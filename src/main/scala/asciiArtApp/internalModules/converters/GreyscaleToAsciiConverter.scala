package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.{AsciiGrid, GreyscaleGrid}
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage}
import asciiArtApp.models.pixels.{AsciiPixel, GreyscalePixel}
import externalModules.converters.Converter
import externalModules.converters.intToCharByTable.IntToCharConverter

class GreyscaleToAsciiConverter(table: IntToCharConverter) extends Converter[GreyscaleImage, AsciiImage] {
  def calculateAsciiValueFromGrey(grey: Int) : Char = {
    table.convert(grey)
  }

  override def convert(item: GreyscaleImage): AsciiImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[AsciiPixel](height, width)
    for (h <- 0 to height) {
      for (w <- 0 to width) {
        val oldPixel = item.getItemOnPos(h, w)
        val newAsciiValue = calculateAsciiValueFromGrey(oldPixel.grey)
        val newPixel = AsciiPixel(newAsciiValue)
        grid(h)(w) = newPixel
      }
    }
    AsciiImage(AsciiGrid(grid))
  }
}
