package asciiArtApp.console.views

import asciiArtApp.console.controllers.Controller
import asciiArtApp.console.parsers.text.{ExporterArgsParser, FilterArgsParser, ImageArgParser, TableArgParser, TextParser}
import asciiArtApp.internalModules.exporters.asciiImage.AsciiImageExporter
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.internalModules.loaders.image.fromFile.ImageFromFileLoader
import asciiArtApp.internalModules.loaders.image.random.RandomImageLoader
import asciiArtApp.models.images.RGBImage
import externalModules.converters.intToCharByTable.IntToCharConverter

class ConsoleView(controller: Controller) {
  def run (args: Array[String]): Unit = {
    val loader: RGBImageLoader = ImageArgParser.apply().parse(args)
    val table: IntToCharConverter = TableArgParser.apply().parse(args)
    val filters: Seq[GreyscaleImageFilter] = FilterArgsParser.apply().parse(args)
    val exporters: Seq[AsciiImageExporter] = ExporterArgsParser.apply().parse(args)

    buildApplication(loader, table, filters, exporters)
  }

  def buildApplication(loader: RGBImageLoader,
                       table: IntToCharConverter,
                       filters: Seq[GreyscaleImageFilter],
                       exporters: Seq[AsciiImageExporter]): Unit = {
    // LOAD
    val rgbImage = controller.loadImage(loader)

    // CONVERT RGB -> GREYSCALE
    var greyscaleImage = controller.convertRGBImageToGreyscaleImage(rgbImage)

    // APPLY FILTERS
    greyscaleImage = controller.applyFiltersToGreyscaleImage(greyscaleImage, filters)

    // CONVERT GREYSCALE -> ASCII
    val asciiImage = controller.convertGreyscaleImageToAscii(greyscaleImage, table)

    // EXPORT
    controller.exportAsciiImage(asciiImage, exporters)
  }
}
