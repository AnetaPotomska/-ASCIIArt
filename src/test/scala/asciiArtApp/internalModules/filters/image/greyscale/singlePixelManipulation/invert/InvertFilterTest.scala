package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import org.scalatest.FunSuite

class InvertFilterTest extends FunSuite {
  def pixelManipulator(value: Int): Int = new InvertFilter().pixelManipulator(value)

  test("Invert 0") {
    val result = pixelManipulator(0)
    assert(result == 255)
  }

  test("Invert 255") {
    val result = pixelManipulator(255)
    assert(result == 0)
  }
}
