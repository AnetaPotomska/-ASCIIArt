package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.images.AsciiImage
import asciiArtApp.models.pixels.AsciiPixel
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class AsciiToStringConverterTest extends FunSuite {
  def convert(item: AsciiImage): Option[String]  = new AsciiToStringConverter().convert(item)

  // ------------------------------------------------------------
  // EMPTY ASCII IMAGE

  test("Empty ascii image") {
    val mockAsciiImage = mock[AsciiImage]

    when(mockAsciiImage.getWidth).thenReturn(0)
    when(mockAsciiImage.getHeight).thenReturn(0)

    val str = convert(mockAsciiImage)
    assert(str.isEmpty)
  }

  // ------------------------------------------------------------
  // SINGLE PIXEL ASCII IMAGE

  test("Single pixel ascii image") {
    val grid = Array.ofDim[AsciiPixel](1, 1)
    grid(0)(0) = AsciiPixel('a')
    val image = AsciiImage(AsciiGrid(grid))

    val str = convert(image)
    assert(str.isDefined)
    assert(str.get == "a\n")
  }

  // ------------------------------------------------------------
  // SQUARE PIXEL ASCII IMAGE

  test("Square ascii image") {
    val grid = Array.ofDim[AsciiPixel](2, 2)
    grid(0)(0) = AsciiPixel('a')
    grid(0)(1) = AsciiPixel('b')
    grid(1)(0) = AsciiPixel('c')
    grid(1)(1) = AsciiPixel('d')
    val image = AsciiImage(AsciiGrid(grid))

    val str = convert(image)
    assert(str.isDefined)
    assert(str.get == "ab\ncd\n")
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height < width)

  test("Rectangle ascii image") {
    val grid = Array.ofDim[AsciiPixel](1, 2)
    grid(0)(0) = AsciiPixel('a')
    grid(0)(1) = AsciiPixel('b')
    val image = AsciiImage(AsciiGrid(grid))

    val str = convert(image)
    assert(str.isDefined)
    assert(str.get == "ab\n")
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height > width)

  test("Rectangle ascii image") {
    val grid = Array.ofDim[AsciiPixel](2, 1)
    grid(0)(0) = AsciiPixel('a')
    grid(1)(0) = AsciiPixel('b')
    val image = AsciiImage(AsciiGrid(grid))

    val str = convert(image)
    assert(str.isDefined)
    assert(str.get == "a\nb\n")
  }
}
