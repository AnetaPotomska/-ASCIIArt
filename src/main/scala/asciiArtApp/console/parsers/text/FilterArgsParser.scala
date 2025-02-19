package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.flip.axis.{FlipXAxisFilter, FlipYAxisFilter}
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness.BrightnessFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert.InvertFilter
import externalModules.converters.stringToInt.StringNumberToIntConverter

class FilterArgsParser() extends TextParser[Seq[GreyscaleImageFilter]] {
  override def parse(source: Array[String]): Seq[GreyscaleImageFilter] = {
    // go through source and look for filter-related arguments
    var toRet = Seq[GreyscaleImageFilter]()
    for(s <- 0 until source.length) {
      source(s) match {
        case "--invert" => toRet = toRet.appended(new InvertFilter)
        // look one ahead for parameter of this argument
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
        // look one ahead for parameter of this argument
        case "--brightness" => {
          if (s + 1 >= source.length) {
            throw new Exception("Missing brightness value")
          }
          val brightnessValueString = source(s + 1)

          // convert this string to number if possible (accepted formats: num, -num, +num)
          val brightnessValue = new StringNumberToIntConverter().convert(brightnessValueString)
          if(brightnessValue.isEmpty) {
            throw new Exception("Given value isn't number")
          }
          toRet = toRet.appended(new BrightnessFilter(brightnessValue.get))
        }
        case _ =>
      }
    }
    toRet
  }
}
