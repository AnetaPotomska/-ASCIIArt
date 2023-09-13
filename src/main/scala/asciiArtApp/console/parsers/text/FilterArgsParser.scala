package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.flip.axis.{FlipXAxisFilter, FlipYAxisFilter}
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness.BrightnessFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert.InvertFilter

case class FilterArgsParser() extends TextParser[Seq[GreyscaleImageFilter]] {
  private def processBrightnessValue(toProcess: String) : Int = {
    var stringNumber = toProcess
    var isNegative = false
    if (stringNumber(0) == '-') {
      isNegative = true
      stringNumber = stringNumber.substring(1)
    }
    // check if all chars are digits
    if (!(stringNumber forall Character.isDigit)) {
      throw new Exception("Brightness value isn't number")
    }
    var brightnessValue = stringNumber.toInt
    if (isNegative) {
      brightnessValue = -1 * brightnessValue
    }
    brightnessValue
  }
  override def parse(source: Array[String]): Seq[GreyscaleImageFilter] = {
    var toRet = Seq[GreyscaleImageFilter]()
    for(s <- 0 until source.length) {
      source(s) match {
        case "--invert" => toRet = toRet.appended(new InvertFilter)
        case "--flip" => {
          if (s + 1 >= source.length) {
            throw new Exception("Missing flip value")
          }
          val value = source(s + 1)
          value match {
            case "x" => toRet = toRet.appended(new FlipXAxisFilter)
            case "y" => toRet = toRet.appended(new FlipYAxisFilter)
            case _ => throw new Exception("Unknown flip value")
          }
        }
        case "--brightness" => {
          if (s + 1 >= source.length) {
            throw new Exception("Missing brightness value")
          }
          val brightnessValueString = source(s + 1)
          val brightnessValue = processBrightnessValue(brightnessValueString)
          toRet = toRet.appended(new BrightnessFilter(brightnessValue))
        }
        case _ =>
      }
    }
    toRet
  }
}
