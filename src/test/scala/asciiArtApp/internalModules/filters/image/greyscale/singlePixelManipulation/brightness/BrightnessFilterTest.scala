package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness

import org.scalatest.FunSuite

class BrightnessFilterTest extends FunSuite {
  def pixelManipulator(value: Int, brightnessValue: Int): Int = new BrightnessFilter(brightnessValue).pixelManipulator(value)

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
}
