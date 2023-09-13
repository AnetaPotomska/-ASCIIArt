package asciiArtApp.console.controllers

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

trait Controller {
  def loadImage(loader: RGBImageLoader) : RGBImage
  def convertRGBImageToGreyscaleImage(rgbImage: RGBImage): GreyscaleImage
  def applyFiltersToGreyscaleImage(greyscaleImage: GreyscaleImage, filters: Seq[GreyscaleImageFilter]): GreyscaleImage
  def convertGreyscaleImageToAscii(greyscaleImage: GreyscaleImage, table: IntToCharConverter): AsciiImage
  def exportAsciiImage(asciiImage: AsciiImage, exporters: Seq[AsciiImageExporter]): Unit
}
