package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class InvertFilterTest extends FunSuite {
  def pixelManipulator(value: Int): Int = new InvertFilter().pixelManipulator(value)

  def filter(item: GreyscaleImage): GreyscaleImage = new InvertFilter().filter(item)

  test("Invert 0") {
    val result = pixelManipulator(0)
    assert(result == 255)
  }

  test("Invert 255") {
    val result = pixelManipulator(255)
    assert(result == 0)
  }

  test("Invert -1") {
    val result = pixelManipulator(-1)
    assert(result == 256)
  }

  test("Invert 256") {
    val result = pixelManipulator(256)
    assert(result == -1)
  }

  test("Filter") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(235))
    assert(imageFiltered.getItemOnPos(0, 1) == GreyscalePixel(225))
    assert(imageFiltered.getItemOnPos(1, 0) == GreyscalePixel(215))
    assert(imageFiltered.getItemOnPos(1, 1) == GreyscalePixel(205))
  }
}
