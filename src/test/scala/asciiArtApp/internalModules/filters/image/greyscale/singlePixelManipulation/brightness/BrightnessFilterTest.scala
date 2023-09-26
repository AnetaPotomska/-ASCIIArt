package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class BrightnessFilterTest extends FunSuite {
  def pixelManipulator(value: Int, brightnessValue: Int): Int = new BrightnessFilter(brightnessValue).pixelManipulator(value)
  def filter(item: GreyscaleImage, brightnessValue: Int): Option[GreyscaleImage] = new BrightnessFilter(brightnessValue).filter(item)

  // ------------------------------------------------------------
  // PIXEL MANIPULATOR

  test("Add brightness to value (result in bounds 0 - 255)") {
    assert(pixelManipulator(255, 0) == 255)
    assert(pixelManipulator(0, 255) == 255)
    assert(pixelManipulator(50, 105) == 155)
    assert(pixelManipulator(1, 2) == 3)
    assert(pixelManipulator(0, 0) == 0)
  }


  test("Add brightness to value (result more than 255)") {
    assert(pixelManipulator(-1,257) == 255)
    assert(pixelManipulator(50, 257) == 255)
    assert(pixelManipulator(-100, 1000) == 255)
    assert(pixelManipulator(589, -9) == 255)
    assert(pixelManipulator(0, 257) == 255)
  }

  test("Add brightness to value (result less than 0)") {
    assert(pixelManipulator(-1, 0) == 0)
    assert(pixelManipulator(50, -257) == 0)
    assert(pixelManipulator(-10000, 1000) == 0)
    assert(pixelManipulator(-589, -9) == 0)
    assert(pixelManipulator(0, -257) == 0)
  }

  // ------------------------------------------------------------
  // EMPTY GREYSCALE IMAGE

  test("Empty greyscale image") {
    val mockGreyscaleImage = mock[GreyscaleImage]

    when(mockGreyscaleImage.getWidth).thenReturn(0)
    when(mockGreyscaleImage.getHeight).thenReturn(0)

    val imageFiltered = filter(mockGreyscaleImage, 50)
    assert(imageFiltered.isEmpty)
  }

  // ------------------------------------------------------------
  // SINGLE PIXEL GREYSCALE IMAGE

  test("Single pixel greyscale image") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(0)
    val greyscaleImage = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(greyscaleImage, 50)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(50))
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

    val imageFiltered = filter(image, -35)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(0))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(0))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(5))
    assert(imageFiltered.get.getItemOnPos(1, 1) == GreyscalePixel(15))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height < width)

  test("Rectangle greyscale image (height < width)") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image, -35)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(0))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(0))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL ASCII IMAGE (height > width)

  test("Rectangle greyscale image (height > width)") {
    val grid = Array.ofDim[GreyscalePixel](2, 1)
    grid(0)(0) = GreyscalePixel(20)
    grid(1)(0) = GreyscalePixel(40)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image, -35)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(0))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(5))
  }
}
