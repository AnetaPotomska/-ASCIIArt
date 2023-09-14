package asciiArtApp.models.grids

import asciiArtApp.models.pixels.AsciiPixel
import org.scalatest.FunSuite

class AsciiGridTest extends FunSuite {
  val grid: Array[Array[AsciiPixel]] = Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')))
  val asciiGrid: AsciiGrid = AsciiGrid(grid)

  test("Get height") {
    val height = asciiGrid.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = asciiGrid.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(asciiGrid.getItemOnPos(0, 0) == AsciiPixel('a'))
    assert(asciiGrid.getItemOnPos(0, 1) == AsciiPixel('b'))
    assert(asciiGrid.getItemOnPos(1, 0) == AsciiPixel('c'))
    assert(asciiGrid.getItemOnPos(1, 1) == AsciiPixel('d'))
  }
}
