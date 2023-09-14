package asciiArtApp.models.images

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.pixels.AsciiPixel
import org.scalatest.FunSuite

class AsciiImageTest extends FunSuite {
  val grid: Array[Array[AsciiPixel]] = Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')))
  val asciiImage: AsciiImage = AsciiImage(AsciiGrid(grid))

  test("Get height") {
    val height = asciiImage.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = asciiImage.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(asciiImage.getItemOnPos(0, 0) == AsciiPixel('a'))
    assert(asciiImage.getItemOnPos(0, 1) == AsciiPixel('b'))
    assert(asciiImage.getItemOnPos(1, 0) == AsciiPixel('c'))
    assert(asciiImage.getItemOnPos(1, 1) == AsciiPixel('d'))
  }
}
