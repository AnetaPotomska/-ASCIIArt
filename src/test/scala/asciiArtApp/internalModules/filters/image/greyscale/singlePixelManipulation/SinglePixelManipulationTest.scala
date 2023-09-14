package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class SinglePixelManipulationTest extends FunSuite {
  class tmp extends SinglePixelManipulation() {
    override def pixelManipulator(value: Int): Int = {
      value + 60
    }
  }

  def pixelManipulator(value: Int): Int = new tmp().pixelManipulator(value)
  def filter(item: GreyscaleImage): GreyscaleImage = new tmp().filter(item)

  test("Custom manipulator") {
    assert(pixelManipulator(5) == 65)
  }

  test("Filter") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(80))
    assert(imageFiltered.getItemOnPos(0, 1) == GreyscalePixel(90))
    assert(imageFiltered.getItemOnPos(1, 0) == GreyscalePixel(100))
    assert(imageFiltered.getItemOnPos(1, 1) == GreyscalePixel(110))
  }
}
