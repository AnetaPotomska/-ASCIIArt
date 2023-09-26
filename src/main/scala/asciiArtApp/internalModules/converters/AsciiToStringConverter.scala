package asciiArtApp.internalModules.converters

import asciiArtApp.models.images.AsciiImage
import externalModules.converters.Converter

class AsciiToStringConverter extends Converter[AsciiImage, String] {
  // read row by row and add newline to every end
  override def convert(item: AsciiImage): Option[String] = {
    val height = item.getHeight
    val width = item.getWidth
    if (height <= 0 || width <= 0) {
      return None
    }
    var toRet = ""
    for (h <- 0 until height) {
      var row = ""
      for (w <- 0 until width) {
        val asciiChar = item.getItemOnPos(h, w)
        row = row.appended(asciiChar.character)
      }
      toRet += row.appended('\n')
    }
    Some(toRet)
  }
}
