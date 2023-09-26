package asciiArtApp.console.controllers

import asciiArtApp.internalModules.converters.RGBToGreyscaleConverter
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage, RGBImage}
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter

trait Controller {
  def loadImage(loader: RGBImageLoader) : RGBImage
  def convertRGBImageToGreyscaleImage(rgbImage: RGBImage, converter: RGBToGreyscaleConverter = new RGBToGreyscaleConverter()): GreyscaleImage
  def applyFiltersToGreyscaleImage(greyscaleImage: GreyscaleImage, filters: Seq[GreyscaleImageFilter]): GreyscaleImage
  def convertGreyscaleImageToAscii(greyscaleImage: GreyscaleImage, table: IntToCharConverter): AsciiImage
  def exportAsciiImage(asciiImage: AsciiImage, exporters: Seq[TextExporter]): Unit
}
