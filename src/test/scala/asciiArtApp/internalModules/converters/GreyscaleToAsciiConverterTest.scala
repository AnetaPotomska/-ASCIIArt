package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{AsciiImage, GreyscaleImage}
import asciiArtApp.models.pixels.{AsciiPixel, GreyscalePixel}
import externalModules.converters.intToCharByTable.IntToCharConverter
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class GreyscaleToAsciiConverterTest extends FunSuite {
  val mockTable: IntToCharConverter = mock[IntToCharConverter]
  def convert(item: GreyscaleImage): Option[AsciiImage]  = new GreyscaleToAsciiConverter(mockTable).convert(item)

  // ------------------------------------------------------------
  // EMPTY GREYSCALE IMAGE

  test("Empty greyscale image to ascii image") {
    val mockGreyscaleImage = mock[GreyscaleImage]

    when(mockGreyscaleImage.getWidth).thenReturn(0)
    when(mockGreyscaleImage.getHeight).thenReturn(0)

    val asciiImage = convert(mockGreyscaleImage)
    assert(asciiImage.isEmpty)
  }

  // ------------------------------------------------------------
  // ERROR WITH CONVERTING

  test("Error with converting greyscale value to ascii value") {
    val mockGreyscaleImage = mock[GreyscaleImage]

    when(mockTable.convert(0)).thenReturn(None)

    val asciiImage = convert(mockGreyscaleImage)
    assert(asciiImage.isEmpty)
  }

  // ------------------------------------------------------------
  // SINGLE PIXEL GREYSCALE IMAGE

  test("Single pixel greyscale image to ascii image") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(0)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    when(mockTable.convert(0)).thenReturn(Some('@'))

    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.isDefined)
    assert(asciiImage.get.getItemOnPos(0, 0) == AsciiPixel('@'))
  }

  // ------------------------------------------------------------
  // SQUARE GREYSCALE IMAGE

  test("Square greyscale image to ascii image") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(0)
    grid(0)(1) = GreyscalePixel(32)
    grid(1)(0) = GreyscalePixel(57)
    grid(1)(1) = GreyscalePixel(112)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    when(mockTable.convert(0)).thenReturn(Some('@'))
    when(mockTable.convert(32)).thenReturn(Some('%'))
    when(mockTable.convert(57)).thenReturn(Some('#'))
    when(mockTable.convert(112)).thenReturn(Some('*'))

    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.isDefined)
    assert(asciiImage.get.getItemOnPos(0, 0) == AsciiPixel('@'))
    assert(asciiImage.get.getItemOnPos(0, 1) == AsciiPixel('%'))
    assert(asciiImage.get.getItemOnPos(1, 0) == AsciiPixel('#'))
    assert(asciiImage.get.getItemOnPos(1, 1) == AsciiPixel('*'))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height < width)

  test("Rectangle greyscale image to ascii image (height < width)") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(0)
    grid(0)(1) = GreyscalePixel(32)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    when(mockTable.convert(0)).thenReturn(Some('@'))
    when(mockTable.convert(32)).thenReturn(Some('%'))

    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.isDefined)
    assert(asciiImage.get.getItemOnPos(0, 0) == AsciiPixel('@'))
    assert(asciiImage.get.getItemOnPos(0, 1) == AsciiPixel('%'))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height > width)

  test("Rectangle greyscale image to ascii image (height > width)") {
    val grid = Array.ofDim[GreyscalePixel](2, 1)
    grid(0)(0) = GreyscalePixel(0)
    grid(1)(0) = GreyscalePixel(57)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    when(mockTable.convert(0)).thenReturn(Some('@'))
    when(mockTable.convert(57)).thenReturn(Some('#'))

    val asciiImage = convert(greyscaleImage)
    assert(asciiImage.isDefined)
    assert(asciiImage.get.getItemOnPos(0, 0) == AsciiPixel('@'))
    assert(asciiImage.get.getItemOnPos(1, 0) == AsciiPixel('#'))
  }
}
