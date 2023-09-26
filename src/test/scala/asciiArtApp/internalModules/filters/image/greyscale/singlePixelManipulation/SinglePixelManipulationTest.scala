package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class SinglePixelManipulationTest extends FunSuite {
  class TestFilter extends SinglePixelManipulation() {
    override def pixelManipulator(value: Int): Int = {
      value + 60
    }
  }

  def filter(item: GreyscaleImage): Option[GreyscaleImage] = new TestFilter().filter(item)

  def pixelManipulator(value: Int): Int = new TestFilter().pixelManipulator(value)

  // ------------------------------------------------------------
  // PIXEL MANIPULATOR

  test("Custom manipulator - positive value") {
    assert(pixelManipulator(5) == 65)
  }

  test("Custom manipulator - negative value") {
    assert(pixelManipulator(-5) == 55)
  }

  test("Custom manipulator - 0") {
    assert(pixelManipulator(0) == 60)
  }

  test("Custom manipulator - return is negative value") {
    assert(pixelManipulator(-65) == -5)
  }

  test("Custom manipulator - return is 0") {
    assert(pixelManipulator(-60) == 0)
  }

  // ------------------------------------------------------------
  // EMPTY GREYSCALE IMAGE

  test("Empty greyscale image") {
    val mockGreyscaleImage = mock[GreyscaleImage]

    when(mockGreyscaleImage.getWidth).thenReturn(0)
    when(mockGreyscaleImage.getHeight).thenReturn(0)

    val imageFiltered = filter(mockGreyscaleImage)
    assert(imageFiltered.isEmpty)
  }

  // ------------------------------------------------------------
  // SINGLE PIXEL GREYSCALE IMAGE

  test("Single pixel greyscale image") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(0)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(greyscaleImage)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(60))
  }

  // ------------------------------------------------------------
  // SQUARE GREYSCALE IMAGE

  test("Square greyscale image") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(80))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(90))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(100))
    assert(imageFiltered.get.getItemOnPos(1, 1) == GreyscalePixel(110))
  }

  // ------------------------------------------------------------
  // RECTANGLE GREYSCALE IMAGE (height < width)

  test("Rectangle greyscale image (height < width)") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(80))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(90))
  }

  // ------------------------------------------------------------
  // RECTANGLE GREYSCALE IMAGE (height > width)

  test("Rectangle greyscale image (height > width)") {
    val grid = Array.ofDim[GreyscalePixel](2, 1)
    grid(0)(0) = GreyscalePixel(20)
    grid(1)(0) = GreyscalePixel(40)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(80))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(100))
  }
}
