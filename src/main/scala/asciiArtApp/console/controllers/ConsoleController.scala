package asciiArtApp.console.controllers

import asciiArtApp.internalModules.converters.{AsciiToStringConverter, GreyscaleToAsciiConverter, RGBToGreyscaleConverter}
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage, RGBImage}
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter

class ConsoleController extends Controller {
  def loadImage(loader: RGBImageLoader) : RGBImage = {
    loader.load()
  }

  override def convertRGBImageToGreyscaleImage(rgbImage: RGBImage): GreyscaleImage = {
    val converter = new RGBToGreyscaleConverter()
    converter.convert(rgbImage)
  }

  override def applyFiltersToGreyscaleImage(greyscaleImage: GreyscaleImage, filters: Seq[GreyscaleImageFilter]): GreyscaleImage = {
    var greyscaleImageToRet = greyscaleImage
    for(f <- filters) {
      greyscaleImageToRet = f.filter(greyscaleImageToRet)
    }
    greyscaleImageToRet
  }

  override def convertGreyscaleImageToAscii(greyscaleImage: GreyscaleImage, table: IntToCharConverter): AsciiImage = {
    val converter = new GreyscaleToAsciiConverter(table)
    converter.convert(greyscaleImage)
  }

  override def exportAsciiImage(asciiImage: AsciiImage, exporters: Seq[TextExporter]): Unit = {
    val asciiToStringConverter = new AsciiToStringConverter()
    val stringFromAsciiImage = asciiToStringConverter.convert(asciiImage)
    for(e <- exporters) {
      e.`export`(stringFromAsciiImage)
    }
  }
}
