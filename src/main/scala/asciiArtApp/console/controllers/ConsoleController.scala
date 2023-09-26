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
    val loadedImage = loader.load()
    if(loadedImage.isEmpty) {
      throw new Exception("Couldn't load image")
    }
    loadedImage.get
  }

  override def convertRGBImageToGreyscaleImage(rgbImage: RGBImage): GreyscaleImage = {
    // RGBToGreyscaleConverter is parameterized with function for calculation grey value from rgb values
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
      val greyscaleImageFiltered = f.filter(greyscaleImageToRet)
      if(greyscaleImageFiltered.isEmpty) {
        throw new Exception("Couldn't apply requested filter(s)")
      }
      greyscaleImageToRet = greyscaleImageFiltered.get
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
    // prepare ascii image for export (convert it to string)
    val asciiToStringConverter = new AsciiToStringConverter()
    val stringFromAsciiImage = asciiToStringConverter.convert(asciiImage)
    if(stringFromAsciiImage.isEmpty) {
      throw new Exception("Couldn't convert ascii to string")
    }

    // export
    for(e <- exporters) {
      e.`export`(stringFromAsciiImage.get)
    }
  }
}
