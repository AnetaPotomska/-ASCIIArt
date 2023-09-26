package asciiArtApp.models.grids

import asciiArtApp.models.pixels.RGBPixel
import org.scalatest.FunSuite

class RGBGridTest extends FunSuite {
  val squareGrid: RGBGrid = RGBGrid(Array(Array(
    RGBPixel(0, 0, 0),
    RGBPixel(1, 1, 1)),
    Array(RGBPixel(2, 2, 2),
      RGBPixel(3, 3, 3))))

  // ------------------------------------------------------------
  // HEIGHT

  test("Get height") {
    val height = squareGrid.getHeight
    assert(height == 2)
  }

  // ------------------------------------------------------------
  // WIDTH

  test("Get width") {
    val width = squareGrid.getWidth
    assert(width == 2)
  }

  // ------------------------------------------------------------
  // SET ITEM ON POS

  test("Set item o pos (in bounds)") {
    val height = 2
    val width = 2
    val newSquareGrid: RGBGrid = RGBGrid(Array.ofDim[RGBPixel](height, width))

    newSquareGrid.setItemOnPos(0, 0, RGBPixel(0, 0, 0))
    newSquareGrid.setItemOnPos(0, 1, RGBPixel(1, 1, 1))
    newSquareGrid.setItemOnPos(1, 0, RGBPixel(2, 2, 2))
    newSquareGrid.setItemOnPos(1, 1, RGBPixel(3, 3, 3))

    assert(newSquareGrid.getItemOnPos(0, 0) == RGBPixel(0, 0, 0))
    assert(newSquareGrid.getItemOnPos(0, 1) == RGBPixel(1, 1, 1))
    assert(newSquareGrid.getItemOnPos(1, 0) == RGBPixel(2, 2, 2))
    assert(newSquareGrid.getItemOnPos(1, 1) == RGBPixel(3, 3, 3))
  }

  test("Set item o pos (out of bounds)") {
    val height = 1
    val width = 1
    val newSquareGrid: RGBGrid = RGBGrid(Array.ofDim[RGBPixel](height, width))

    val caught =
      intercept[Exception] {
        newSquareGrid.setItemOnPos(0, 1, RGBPixel(1, 1, 1))
      }
    assert(caught.getMessage == "Invalid coordinates")
  }

  // ------------------------------------------------------------
  // GET ITEM ON POS

  test("Get item o pos (in bounds)") {
    assert(squareGrid.getItemOnPos(0, 0) == RGBPixel(0, 0, 0))
    assert(squareGrid.getItemOnPos(0, 1) == RGBPixel(1, 1, 1))
    assert(squareGrid.getItemOnPos(1, 0) == RGBPixel(2, 2, 2))
    assert(squareGrid.getItemOnPos(1, 1) == RGBPixel(3, 3, 3))
  }

  test("Get item o pos (out of bounds)") {
    val caught =
      intercept[Exception] {
        squareGrid.getItemOnPos(0, 2) == RGBPixel(0, 0, 0)
      }
    assert(caught.getMessage == "Invalid coordinates")
  }

  // ------------------------------------------------------------
  // CHECK COORDINATION

  test("Negative coordination") {
    assert(!squareGrid.checkCoordination(-1, -1))
    assert(!squareGrid.checkCoordination(-1, 0))
    assert(!squareGrid.checkCoordination(0, -1))
  }

  test("X larger than height") {
    assert(!squareGrid.checkCoordination(2, 0))
    assert(!squareGrid.checkCoordination(5, 0))
    assert(!squareGrid.checkCoordination(9, 0))
  }

  test("Y larger than width") {
    assert(!squareGrid.checkCoordination(0, 2))
    assert(!squareGrid.checkCoordination(0, 5))
    assert(!squareGrid.checkCoordination(0, 9))
  }

  // ------------------------------------------------------------
  // ITERATOR

  test("Doesn't have next") {
    val height = 0
    val width = 0
    val newSquareGrid: RGBGrid = RGBGrid(Array.ofDim[RGBPixel](height, width))

    assert(!newSquareGrid.iterator.hasNext)
  }

  test("Go through grid with iterator") {
    val iterator = squareGrid.iterator
    var coord = iterator.next
    assert(coord._1 == 0 && coord._2 == 0)
    coord = iterator.next
    assert(coord._1 == 0 && coord._2 == 1)
    coord = iterator.next
    assert(coord._1 == 1 && coord._2 == 0)
    coord = iterator.next
    assert(coord._1 == 1 && coord._2 == 1)
    val caught =
      intercept[Exception] {
        coord = iterator.next
      }
    assert(caught.getMessage == "No more elements in the grid")
  }
}
