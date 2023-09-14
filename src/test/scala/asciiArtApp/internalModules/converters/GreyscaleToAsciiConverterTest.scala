package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage}
import asciiArtApp.models.pixels.{AsciiPixel, GreyscalePixel}
import externalModules.converters.intToCharByTable.nonLinear.FunNonLinearConverter
import org.scalatest.FunSuite

class GreyscaleToAsciiConverterTest extends FunSuite {
  def convert(item: GreyscaleImage): AsciiImage  = new GreyscaleToAsciiConverter(new FunNonLinearConverter).convert(item)

  // ------------------------------------------------------------
  // SINGLE PIXEL ASCII IMAGE

  test("Single pixel greyscale image to ascii image") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(0)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))
    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.getItemOnPos(0, 0) == AsciiPixel('@'))
  }

  // ------------------------------------------------------------
  // BIG ASCII IMAGE

  test("Greyscale image to ascii image") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(0)
    grid(0)(1) = GreyscalePixel(32)
    grid(1)(0) = GreyscalePixel(57)
    grid(1)(1) = GreyscalePixel(112)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))
    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.getItemOnPos(0, 0) == AsciiPixel('@'))
    assert(asciiImage.getItemOnPos(0, 1) == AsciiPixel('%'))
    assert(asciiImage.getItemOnPos(1, 0) == AsciiPixel('#'))
    assert(asciiImage.getItemOnPos(1, 1) == AsciiPixel('*'))
  }
}
