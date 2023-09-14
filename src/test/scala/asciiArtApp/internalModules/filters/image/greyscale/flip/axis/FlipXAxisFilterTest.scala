package asciiArtApp.internalModules.filters.image.greyscale.flip.axis

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.GreyscaleImage
import asciiArtApp.models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class FlipXAxisFilterTest extends FunSuite {
  def filter(item: GreyscaleImage): GreyscaleImage = new FlipXAxisFilter().filter(item)

  // ------------------------------------------------------------
  // SINGLE PIXEL IMAGE
  test("Single pixel image flip by x") {
    val grid = Array.ofDim[GreyscalePixel](1, 1)
    grid(0)(0) = GreyscalePixel(20)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(20))
  }

  // ------------------------------------------------------------
  // SQUARE PIXEL IMAGE
  test("Square image flip by x") {
    val grid = Array.ofDim[GreyscalePixel](2, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    grid(1)(0) = GreyscalePixel(40)
    grid(1)(1) = GreyscalePixel(50)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(40))
    assert(imageFiltered.getItemOnPos(0, 1) == GreyscalePixel(50))
    assert(imageFiltered.getItemOnPos(1, 0) == GreyscalePixel(20))
    assert(imageFiltered.getItemOnPos(1, 1) == GreyscalePixel(30))
  }

  // ------------------------------------------------------------
  // RECTANGLE PIXEL IMAGE
  test("Rectangle image flip by x") {
    val grid = Array.ofDim[GreyscalePixel](1, 2)
    grid(0)(0) = GreyscalePixel(20)
    grid(0)(1) = GreyscalePixel(30)
    val image = GreyscaleImage(GreyscaleGrid(grid))
    val imageFiltered = filter(image)
    assert(imageFiltered.getItemOnPos(0, 0) == GreyscalePixel(20))
    assert(imageFiltered.getItemOnPos(0, 1) == GreyscalePixel(30))
  }
}
