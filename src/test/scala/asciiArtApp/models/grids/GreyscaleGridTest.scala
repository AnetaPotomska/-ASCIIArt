package asciiArtApp.models.grids

import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class GreyscaleGridTest extends FunSuite {
  val grid: Array[Array[GreyscalePixel]] = Array(Array(GreyscalePixel(1), GreyscalePixel(2)), Array(GreyscalePixel(3), GreyscalePixel(4)))
  val greyscaleGrid: GreyscaleGrid = GreyscaleGrid(grid)

  test("Get height") {
    val height = greyscaleGrid.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = greyscaleGrid.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(greyscaleGrid.getItemOnPos(0, 0) == GreyscalePixel(1))
    assert(greyscaleGrid.getItemOnPos(0, 1) == GreyscalePixel(2))
    assert(greyscaleGrid.getItemOnPos(1, 0) == GreyscalePixel(3))
    assert(greyscaleGrid.getItemOnPos(1, 1) == GreyscalePixel(4))
  }
}
