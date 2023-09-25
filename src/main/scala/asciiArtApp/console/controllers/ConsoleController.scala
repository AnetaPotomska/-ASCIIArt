package asciiArtApp.console.controllers

import asciiArtApp.internalModules.converters.{AsciiToStringConverter, GreyscaleToAsciiConverter, RGBToGreyscaleConverter}
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.RGBPixel
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter

class ConsoleController extends Controller {
  def loadImage(loader: RGBImageLoader) : RGBImage = {
    loader.load()
  }

  override def convertRGBImageToGreyscaleImage(rgbImage: RGBImage): GreyscaleImage = {
    val converter = new RGBToGreyscaleConverter((pixel: RGBPixel) => ((0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)).floor.toInt)
    val toRet = converter.convert(rgbImage)
    if(toRet.isEmpty) {
      throw new Exception("Couldn't convert rgb image to grayscale image")
    }
    toRet.get
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
    val toRet = converter.convert(greyscaleImage)
    if (toRet.isEmpty) {
      throw new Exception("Couldn't convert greyscale image to ascii")
    }
    toRet.get
  }

  override def exportAsciiImage(asciiImage: AsciiImage, exporters: Seq[TextExporter]): Unit = {
    val asciiToStringConverter = new AsciiToStringConverter()
    val stringFromAsciiImage = asciiToStringConverter.convert(asciiImage)
    if(stringFromAsciiImage.isEmpty) {
      throw new Exception("Couldn't convert ascii to string")
    }
    for(e <- exporters) {
      e.`export`(stringFromAsciiImage.get)
    }
  }
}
