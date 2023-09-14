package asciiArtApp.models.grids

import asciiArtApp.models.pixels.RGBPixel
import org.scalatest.FunSuite

class RGBGridTest extends FunSuite {
  val grid: Array[Array[RGBPixel]] = Array(Array(RGBPixel(1, 1, 1), RGBPixel(2, 2, 2)), Array(RGBPixel(3, 3, 3), RGBPixel(4, 4, 4)))
  val rgbGrid = new RGBGrid(grid)

  test("Get height") {
    val height = rgbGrid.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = rgbGrid.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(rgbGrid.getItemOnPos(0, 0) == RGBPixel(1, 1, 1))
    assert(rgbGrid.getItemOnPos(0, 1) == RGBPixel(2, 2, 2))
    assert(rgbGrid.getItemOnPos(1, 0) == RGBPixel(3, 3, 3))
    assert(rgbGrid.getItemOnPos(1, 1) == RGBPixel(4, 4, 4))
  }
}
