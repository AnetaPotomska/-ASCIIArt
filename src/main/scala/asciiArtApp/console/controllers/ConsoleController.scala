package asciiArtApp.console.controllers
import asciiArtApp.internalModules.converters.{GreyscaleToAsciiConverter, RGBToGreyscaleConverter}
import asciiArtApp.internalModules.exporters.asciiImage.AsciiImageExporter
import asciiArtApp.internalModules.filters.Filter
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.Loader
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.internalModules.loaders.image.fromFile.ImageFromFileLoader
import asciiArtApp.internalModules.loaders.image.random.RandomImageLoader
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage, Image, RGBImage}
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.Exporter

import java.io.File

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

  override def exportAsciiImage(asciiImage: AsciiImage, exporters: Seq[AsciiImageExporter]): Unit = {
    for(e <- exporters) {
      e.`export`(asciiImage)
    }
  }
}
