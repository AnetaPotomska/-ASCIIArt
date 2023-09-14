package asciiArtApp.console.views

import asciiArtApp.console.controllers.Controller
import asciiArtApp.console.parsers.text.{ExporterArgsParser, FilterArgsParser, ImageArgParser, TableArgParser}
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter

class ConsoleView(controller: Controller) {
  def run (args: Array[String]): Unit = {
    val loader: RGBImageLoader = new ImageArgParser().parse(args)
    val table: IntToCharConverter = new TableArgParser().parse(args)
    val filters: Seq[GreyscaleImageFilter] = new FilterArgsParser().parse(args)
    val exporters: Seq[TextExporter] = new ExporterArgsParser().parse(args)

    buildApplication(loader, table, filters, exporters)
  }

  // FIXME
  /*
  A good indicator that you are doing it wrong is (awkwardly) storing some data (images) between controller invokes.
   */
  private def buildApplication(loader: RGBImageLoader,
                               table: IntToCharConverter,
                               filters: Seq[GreyscaleImageFilter],
                               exporters: Seq[TextExporter]): Unit = {
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
