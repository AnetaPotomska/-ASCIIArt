package asciiArtApp.models.images

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class GreyscaleImageTest extends FunSuite {
  val grid: Array[Array[GreyscalePixel]] = Array(Array(GreyscalePixel(1), GreyscalePixel(2)), Array(GreyscalePixel(3), GreyscalePixel(4)))
  val greyscaleImage: GreyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

  test("Get height") {
    val height = greyscaleImage.getHeight
    assert(height == 2)
  }

  test("Get width") {
    val width = greyscaleImage.getWidth
    assert(width == 2)
  }

  test("Get item o pos") {
    assert(greyscaleImage.getItemOnPos(0, 0) == GreyscalePixel(1))
    assert(greyscaleImage.getItemOnPos(0, 1) == GreyscalePixel(2))
    assert(greyscaleImage.getItemOnPos(1, 0) == GreyscalePixel(3))
    assert(greyscaleImage.getItemOnPos(1, 1) == GreyscalePixel(4))
  }
}
