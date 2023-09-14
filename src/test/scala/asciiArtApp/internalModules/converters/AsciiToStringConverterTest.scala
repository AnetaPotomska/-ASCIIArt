package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.images.AsciiImage
import asciiArtApp.models.pixels.AsciiPixel
import org.scalatest.FunSuite

class AsciiToStringConverterTest extends FunSuite {
  def convert(item: AsciiImage): String  = new AsciiToStringConverter().convert(item)

  // ------------------------------------------------------------
  // SINGLE PIXEL ASCII IMAGE

  test("Single pixel ascii image") {
    val grid = Array.ofDim[AsciiPixel](1, 1)
    grid(0)(0) = AsciiPixel('a')
    val image = AsciiImage(AsciiGrid(grid))
    val str = convert(image)
    assert(str == "a\n")
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
    assert(str == "ab\ncd\n")
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE

  test("Rectangle ascii image") {
    val grid = Array.ofDim[AsciiPixel](1, 2)
    grid(0)(0) = AsciiPixel('a')
    grid(0)(1) = AsciiPixel('b')
    val image = AsciiImage(AsciiGrid(grid))
    val str = convert(image)
    assert(str == "ab\n")
  }
}
