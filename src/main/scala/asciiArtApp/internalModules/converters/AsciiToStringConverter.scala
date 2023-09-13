package asciiArtApp.internalModules.converters

import asciiArtApp.models.images.AsciiImage
import externalModules.converters.Converter

class AsciiToStringConverter extends Converter[AsciiImage, String] {
  override def convert(item: AsciiImage): String = {
    val height = item.getHeight
    val width = item.getWidth
    var toRet = ""
    for (h <- 0 until height) {
      var row = ""
      for (w <- 0 until width) {
        val asciiChar = item.getItemOnPos(h, w)
        row = row.appended(asciiChar.character)
      }
      toRet += row.appended('\n')
    }
    toRet
  }
}
