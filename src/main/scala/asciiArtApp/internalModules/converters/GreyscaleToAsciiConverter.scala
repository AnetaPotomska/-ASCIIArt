package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage}
import asciiArtApp.models.pixels.AsciiPixel
import externalModules.converters.Converter
import externalModules.converters.intToCharByTable.IntToCharConverter

class GreyscaleToAsciiConverter(table: IntToCharConverter) extends Converter[GreyscaleImage, AsciiImage] {
  private def calculateAsciiValueFromGrey(grey: Int) : Char = {
    table.convert(grey)
  }

  override def convert(item: GreyscaleImage): AsciiImage = {
    val height = item.getHeight
    val width = item.getWidth
    val grid = Array.ofDim[AsciiPixel](width, height)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val oldPixel = item.getItemOnPos(h, w)
        val newAsciiValue = calculateAsciiValueFromGrey(oldPixel.grey)
        val newPixel = AsciiPixel(newAsciiValue)
        grid(w)(h) = newPixel
      }
    }
    AsciiImage(AsciiGrid(grid))
  }
}
