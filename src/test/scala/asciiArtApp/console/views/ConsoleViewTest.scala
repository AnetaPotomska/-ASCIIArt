package asciiArtApp.console.views

import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.parsers.text.{ExportArgsParser, FilterArgsParser, ImageArgParser, TableArgParser}
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter
import org.mockito.MockitoSugar.{verify, verifyZeroInteractions, when}
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class ConsoleViewTest extends FunSuite {
  val mockController: ConsoleController = mock[ConsoleController]

  val mockImageParser: ImageArgParser = mock[ImageArgParser]
  val mockTableParser: TableArgParser = mock[TableArgParser]
  val mockFilterParser: FilterArgsParser = mock[FilterArgsParser]
  val mockExporterParser: ExportArgsParser = mock[ExportArgsParser]

  val mockLoader: RGBImageLoader = mock[RGBImageLoader]
  val mockTable: IntToCharConverter = mock[IntToCharConverter]
  val mockFilter: GreyscaleImageFilter = mock[GreyscaleImageFilter]
  val mockExport: TextExporter = mock[TextExporter]

/*
  // ------------------------------------------------------------
  // LOAD

  test("Random image arg") {
    // given
    val args = Array("--image-random")
    val view = new ConsoleView(mockController)

    // when
    when(mockImageParser.parse(args)).thenReturn(mockLoader)
    when(mockTableParser.parse(args)).thenReturn(mockTable)
    when(mockFilterParser.parse(args)).thenReturn(Seq(mockFilter))
    when(mockExporterParser.parse(args)).thenReturn(Seq(mockExport))
    view.run(args)

    // then
    verify(mockImageParser).parse(args)
    verify(mockController).loadImage(mockLoader)
  }

  test("Image from path arg") {
    // given
    val args = Array("--image-path", "path")
    val view = new ConsoleView(mockController)

    // when
    when(mockImageParser.parse(args)).thenReturn(mockLoader)
    view.run(args)

    // then
    verify(mockImageParser).parse(args)
    verify(mockController).loadImage(mockLoader)
  }

  test("Image more than 1 argument") {
    // given
    val args = Array("--image-path", "path", "--image-random")
    val view = new ConsoleView(mockController)

    // when
    when(mockImageParser.parse(args)).thenThrow(new Exception("Exception"))
    view.run(args)

    // then
    verify(mockImageParser).parse(args)
    verifyZeroInteractions(mockController.loadImage(mockLoader))
  }*/
}

