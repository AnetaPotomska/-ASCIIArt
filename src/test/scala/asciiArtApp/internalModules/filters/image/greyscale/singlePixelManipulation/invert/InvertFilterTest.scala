package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class InvertFilterTest extends FunSuite {
  def pixelManipulator(value: Int): Int = new InvertFilter().pixelManipulator(value)

  def filter(item: GreyscaleImage): Option[GreyscaleImage] = new InvertFilter().filter(item)


  // ------------------------------------------------------------
  // PIXEL MANIPULATOR

  test("In bounds of 0 - 255") {
    assert(pixelManipulator(20) == 235)
    assert(pixelManipulator(55) == 200)
    assert(pixelManipulator(155) == 100)
  }

  test("Invert 0") {
    assert(pixelManipulator(0) == 255)
  }

  test("Invert 255") {
    assert(pixelManipulator(255) == 0)
  }

  test("Invert negative") {
    assert(pixelManipulator(-1) == 255)
    assert(pixelManipulator(-145) == 255)
    assert(pixelManipulator(-257245) == 255)
  }

  test("Invert more than 255") {
    assert(pixelManipulator(256) == 0)
    assert(pixelManipulator(356) == 0)
    assert(pixelManipulator(57854) == 0)
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
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(255))
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
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(235))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(225))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(215))
    assert(imageFiltered.get.getItemOnPos(1, 1) == GreyscalePixel(205))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height < width)

  test("Rectangle greyscale image (height < width)") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(235))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(225))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height > width)

  test("Rectangle greyscale image (height > width)") {
    val grid = Array.ofDim[GreyscalePixel](2, 1)
    grid(0)(0) = GreyscalePixel(20)
    grid(1)(0) = GreyscalePixel(40)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(235))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(215))
  }
}
