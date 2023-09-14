package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class BrightnessFilterTest extends FunSuite {
  def pixelManipulator(value: Int, brightnessValue: Int): Int = new BrightnessFilter(brightnessValue).pixelManipulator(value)
  def filter(item: GreyscaleImage, brightnessValue: Int): GreyscaleImage = new BrightnessFilter(brightnessValue).filter(item)

  test("In bounds of 0 - 255") {
    val result = pixelManipulator(20, 70)
    assert(result == 90)
  }

  test("More than 255") {
    val result = pixelManipulator(200, 70)
    assert(result == 255)
  }

  test("Less than 0") {
    val result = pixelManipulator(200, -201)
    assert(result == 0)
  }

  test("Filter") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image, -35)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(0))
    assert(imageFiltered.getItemOnPos(0, 1) == GreyscalePixel(0))
    assert(imageFiltered.getItemOnPos(1, 0) == GreyscalePixel(5))
    assert(imageFiltered.getItemOnPos(1, 1) == GreyscalePixel(15))
  }
}
