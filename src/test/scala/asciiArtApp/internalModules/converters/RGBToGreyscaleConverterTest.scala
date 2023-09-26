package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.{GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class RGBToGreyscaleConverterTest extends FunSuite {
  val mockConvertFunc: RGBPixel => Int = mock[RGBPixel => Int]
  def convert(item: RGBImage): Option[GreyscaleImage] = new RGBToGreyscaleConverter(mockConvertFunc).convert(item)

  // ------------------------------------------------------------
  // EMPTY ASCII IMAGE

  test("Empty greyscale image to ascii image") {
    val mockRGBImage = mock[RGBImage]

    when(mockRGBImage.getWidth).thenReturn(0)
    when(mockRGBImage.getHeight).thenReturn(0)

    val asciiImage = convert(mockRGBImage)
    assert(asciiImage.isEmpty)
  }

  // ------------------------------------------------------------
  // SINGLE PIXEL ASCII IMAGE

  test("Single pixel rgb image to greyscale image") {
    val grid = Array.ofDim[RGBPixel](1, 1)
    grid(0)(0) = RGBPixel(0, 10, 20)
    val rgbImage = RGBImage(RGBGrid(grid))

    when(mockConvertFunc(RGBPixel(0, 10, 20))).thenReturn(50)

    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.isDefined)
    assert(greyscaleImage.get.getItemOnPos(0, 0) == GreyscalePixel(50))
  }


  // ------------------------------------------------------------
  // SQUARE GREYSCALE IMAGE

  test("Square rgb image to greyscale image") {
    val grid = Array.ofDim[RGBPixel](2, 2)
    grid(0)(0) = RGBPixel(0, 0, 0)
    grid(0)(1) = RGBPixel(32, 32, 32)
    grid(1)(0) = RGBPixel(57, 57, 57)
    grid(1)(1) = RGBPixel(112, 112, 112)
    val rgbImage = RGBImage(RGBGrid(grid))

    when(mockConvertFunc(RGBPixel(0, 0, 0))).thenReturn(50)
    when(mockConvertFunc(RGBPixel(32, 32, 32))).thenReturn(60)
    when(mockConvertFunc(RGBPixel(57, 57, 57))).thenReturn(70)
    when(mockConvertFunc(RGBPixel(112, 112, 112))).thenReturn(80)

    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.isDefined)
    assert(greyscaleImage.get.getItemOnPos(0, 0) == GreyscalePixel(50))
    assert(greyscaleImage.get.getItemOnPos(0, 1) == GreyscalePixel(60))
    assert(greyscaleImage.get.getItemOnPos(1, 0) == GreyscalePixel(70))
    assert(greyscaleImage.get.getItemOnPos(1, 1) == GreyscalePixel(80))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height < width)

  test("Rectangle rgb image to greyscale image (height < width)") {
    val grid = Array.ofDim[RGBPixel](1, 2)
    grid(0)(0) = RGBPixel(0, 0, 0)
    grid(0)(1) = RGBPixel(32, 32, 32)
    val rgbImage = RGBImage(RGBGrid(grid))

    when(mockConvertFunc(RGBPixel(0, 0, 0))).thenReturn(50)
    when(mockConvertFunc(RGBPixel(32, 32, 32))).thenReturn(60)

    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.isDefined)
    assert(greyscaleImage.get.getItemOnPos(0, 0) == GreyscalePixel(50))
    assert(greyscaleImage.get.getItemOnPos(0, 1) == GreyscalePixel(60))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height > width)

  test("Rectangle rgb image to greyscale image (height > width)") {
    val grid = Array.ofDim[RGBPixel](2, 1)
    grid(0)(0) = RGBPixel(0, 0, 0)
    grid(1)(0) = RGBPixel(57, 57, 57)
    val rgbImage = RGBImage(RGBGrid(grid))

    when(mockConvertFunc(RGBPixel(0, 0, 0))).thenReturn(50)
    when(mockConvertFunc(RGBPixel(57, 57, 57))).thenReturn(70)

    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.isDefined)
    assert(greyscaleImage.get.getItemOnPos(0, 0) == GreyscalePixel(50))
    assert(greyscaleImage.get.getItemOnPos(1, 0) == GreyscalePixel(70))
  }
}
