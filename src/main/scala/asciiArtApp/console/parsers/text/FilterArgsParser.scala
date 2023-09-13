package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.exporters.asciiImage.AsciiImageExporter
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.flip.axis.{FlipXAxisFilter, FlipYAxisFilter}
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness.BrightnessFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert.InvertFilter

case class FilterArgsParser() extends TextParser[Seq[GreyscaleImageFilter]] {
  override def parse(source: Array[String]): Seq[GreyscaleImageFilter] = {
    val toRet = Seq[GreyscaleImageFilter]()
    var ignore = false
    for(s <- 0 until source.length) {
      if(!ignore) {
        source(s) match {
          case "--invert" => toRet.appended(new InvertFilter)
          case "--flip x" => toRet.appended(new FlipXAxisFilter)
          case "--flip y" => toRet.appended(new FlipYAxisFilter)
          case "--brightness" => {
            if (s + 1 >= source.length) {
              throw new Exception("Missing brightness value")
            }
            val brightnessValueString = source(s + 1)
            if (!(brightnessValueString forall Character.isDigit)) {
              throw new Exception("Brightness value isn't number")
            }
            toRet.appended(new BrightnessFilter(brightnessValueString.toInt))
            ignore = true
          }
          case _ =>
        }
      }
      else {
        ignore = false
      }
    }
    toRet
  }
}
