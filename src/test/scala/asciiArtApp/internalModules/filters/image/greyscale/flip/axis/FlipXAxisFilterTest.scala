package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class FlipXAxisFilterTest extends FunSuite {
  def filter(item: GreyscaleImage): Option[GreyscaleImage] = new FlipXAxisFilter().filter(item)

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
  // SINGLE PIXEL IMAGE
  test("Single pixel image flip by x") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(20)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(20))
  }

  // ------------------------------------------------------------
  // SQUARE IMAGE
  test("Square image flip by x") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(40))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(50))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(20))
    assert(imageFiltered.get.getItemOnPos(1, 1) == GreyscalePixel(30))
  }

  // ------------------------------------------------------------
  // RECTANGLE IMAGE (height < width)
  test("Rectangle image flip by x (height < width)") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(20))
    assert(imageFiltered.get.getItemOnPos(0, 1) == GreyscalePixel(30))
  }

  // ------------------------------------------------------------
  // RECTANGLE IMAGE (height > width)
  test("Square image flip by x (height > width)") {
    val grid = Array.ofDim[GreyscalePixel](2, 1)
    grid(0)(0) = GreyscalePixel(20)
    grid(1)(0) = GreyscalePixel(40)
    val image = GreyscaleImage(GreyscaleGrid(grid))

    val imageFiltered = filter(image)
    assert(imageFiltered.isDefined)
    assert(imageFiltered.get.getItemOnPos(0, 0) == GreyscalePixel(40))
    assert(imageFiltered.get.getItemOnPos(1, 0) == GreyscalePixel(20))
  }
}
