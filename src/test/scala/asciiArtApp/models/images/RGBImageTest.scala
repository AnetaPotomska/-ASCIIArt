package asciiArtApp.models.images

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.pixels.RGBPixel
import org.scalatest.FunSuite

class RGBImageTest extends FunSuite {
  val grid: Array[Array[RGBPixel]] = Array(Array(RGBPixel(1, 1, 1), RGBPixel(2, 2, 2)), Array(RGBPixel(3, 3, 3), RGBPixel(4, 4, 4)))
  val rgbImage: RGBImage = RGBImage(RGBGrid(grid))

  test("Get height") {
    val height = rgbImage.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = rgbImage.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(rgbImage.getItemOnPos(0, 0) == RGBPixel(1, 1, 1))
    assert(rgbImage.getItemOnPos(0, 1) == RGBPixel(2, 2, 2))
    assert(rgbImage.getItemOnPos(1, 0) == RGBPixel(3, 3, 3))
    assert(rgbImage.getItemOnPos(1, 1) == RGBPixel(4, 4, 4))
  }
}
