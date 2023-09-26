package asciiArtApp.console.controllers

import asciiArtApp.internalModules.converters.{AsciiToStringConverter, GreyscaleToAsciiConverter, RGBToGreyscaleConverter}
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.grids.{GreyscaleGrid, RGBGrid}
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.exporters.text.TextExporter
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class ConsoleControllerTest extends FunSuite {
  val controller = new ConsoleController()

  // ------------------------------------------------------------
  // LOAD IMAGE

  test("loadImage should throw exception when couldn't load image") {
    // given
    val mockLoader = mock[RGBImageLoader]

    // when
    when(mockLoader.load()).thenReturn(None)

    // then
    val caught =
      intercept[Exception] {
        controller.loadImage(mockLoader)
      }
    assert(caught.getMessage == "Couldn't load image")
  }

  test("loadImage should return loaded image when loader loads image correctly") {
    // given
    val mockLoader = mock[RGBImageLoader]
    val grid = Array.ofDim[RGBPixel](1, 2)
    val loadedImage = RGBImage(RGBGrid(grid))
    loadedImage.setItemOnPos(0, 0, RGBPixel(10, 20, 30))
    loadedImage.setItemOnPos(0, 1, RGBPixel(10, 20, 30))

    // when
    when(mockLoader.load()).thenReturn(Some(loadedImage))

    // then
    val resultImage = controller.loadImage(mockLoader)
    assert(resultImage == loadedImage)
  }

  // ------------------------------------------------------------
  // CONVERT RGB IMAGE TO GREYSCALE IMAGE

  test("convertRGBImageToGreyscaleImage should throw exception when couldn't convert rgb image") {
    // given
    val mockConverter = mock[RGBToGreyscaleConverter]
    val mockRGBImage = mock[RGBImage]

    // when
    when(mockConverter.convert(mockRGBImage)).thenReturn(None)

    // then
    val caught =
      intercept[Exception] {
        controller.convertRGBImageToGreyscaleImage(mockRGBImage)
      }
    assert(caught.getMessage == "Couldn't convert rgb image to grayscale image")
  }

  test("convertRGBImageToGreyscaleImage should return greyscale image when converter converts rgb image correctly") {
    // given
    val mockConverter = mock[RGBToGreyscaleConverter]
    val mockRGBImage = mock[RGBImage]

    val greyGrid = Array.ofDim[GreyscalePixel](1, 2)
    val greyImage = GreyscaleImage(GreyscaleGrid(greyGrid))
    greyImage.setItemOnPos(0, 0, GreyscalePixel(10))
    greyImage.setItemOnPos(0, 1, GreyscalePixel(10))

    // when
    when(mockConverter.convert(mockRGBImage)).thenReturn(Some(greyImage))

    // then
    val resultImage = controller.convertRGBImageToGreyscaleImage(mockRGBImage, mockConverter)
    assert(resultImage == greyImage)
  }

  // ------------------------------------------------------------
  // APPLY FILTERS TO GREYSCALE IMAGE

  test("applyFiltersToGreyscaleImage should throw exception when couldn't filter image") {
    // given
    val mockFilter = mock[GreyscaleImageFilter]
    val mockGreyImage = mock[GreyscaleImage]
    val filters: Seq[GreyscaleImageFilter] = Seq(mockFilter)

    // when
    when(mockFilter.filter(mockGreyImage)).thenReturn(None)

    // then
    val caught =
      intercept[Exception] {
        controller.applyFiltersToGreyscaleImage(mockGreyImage, filters)
      }
    assert(caught.getMessage == "Couldn't apply requested filter(s)")
  }

  test("convertRGBImageToGreyscaleImage should return greyscale image when filters filter successfully") {
    // given
    val mockFilter = mock[GreyscaleImageFilter]
    val mockGreyImage1 = mock[GreyscaleImage]
    val mockGreyImage2 = mock[GreyscaleImage]
    val filters: Seq[GreyscaleImageFilter] = Seq(mockFilter)

    // when
    when(mockFilter.filter(mockGreyImage1)).thenReturn(Some(mockGreyImage2))

    // then
    val resultImage = controller.applyFiltersToGreyscaleImage(mockGreyImage1, filters)
    assert(resultImage == mockGreyImage2)
  }

  // ------------------------------------------------------------
  // CONVERT GREYSCALE IMAGE TO ASCII

  test("convertGreyscaleImageToAscii should throw exception when couldn't convert greyscale image") {
    // given
    val mockTable = mock[IntToCharConverter]
    val mockConverter = mock[GreyscaleToAsciiConverter]
    val mockGreyImage = mock[GreyscaleImage]

    // when
    when(mockConverter.convert(mockGreyImage)).thenReturn(None)

    // then
    val caught =
      intercept[Exception] {
        controller.convertGreyscaleImageToAscii(mockGreyImage, mockTable)
      }
    assert(caught.getMessage == "Couldn't convert greyscale image to ascii")
  }

  // ------------------------------------------------------------
  // EXPORT ASCII IMAGE

  test("exportAsciiImage should throw exception when couldn't prepare image for export") {
    // given
    val mockConvertor = mock[AsciiToStringConverter]
    val mockAsciiImage = mock[AsciiImage]
    val mockExporter = mock[TextExporter]
    val exporters: Seq[TextExporter] = Seq(mockExporter)

    // when
    when(mockConvertor.convert(mockAsciiImage)).thenReturn(None)

    // then
    val caught =
      intercept[Exception] {
        controller.exportAsciiImage(mockAsciiImage, exporters)
      }
    assert(caught.getMessage == "Couldn't convert ascii to string")
  }
}