package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.flip.axis.{FlipXAxisFilter, FlipYAxisFilter}
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness.BrightnessFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert.InvertFilter

case class FilterArgsParser() extends TextParser[Seq[GreyscaleImageFilter]] {
  override def parse(source: Array[String]): Seq[GreyscaleImageFilter] = {
    var toRet = Seq[GreyscaleImageFilter]()
    for(s <- 0 until source.length) {
      source(s) match {
        case "--invert" => toRet = toRet.appended(new InvertFilter)
        case "--flip x" => toRet = toRet.appended(new FlipXAxisFilter)
        case "--flip y" => toRet = toRet.appended(new FlipYAxisFilter)
        case "--brightness" => {
          if (s + 1 >= source.length) {
            throw new Exception("Missing brightness value")
          }
          var brightnessValueString = source(s + 1)
          var isNegative = false
          if(brightnessValueString(0) == '-') {
            isNegative = true
            brightnessValueString = brightnessValueString.substring(1)
          }
          // check if all chars are digits
          if (!(brightnessValueString forall Character.isDigit)) {
            throw new Exception("Brightness value isn't number")
          }
          var brightnessValue = brightnessValueString.toInt
          if(isNegative) {
            brightnessValue = -1 * brightnessValue
          }
          toRet = toRet.appended(new BrightnessFilter(brightnessValue))
        }
        case _ =>
      }
    }
    toRet
  }
}
