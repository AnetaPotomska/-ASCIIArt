package asciiArtApp.models.grids

import asciiArtApp.models.pixels.AsciiPixel
import org.scalatest.FunSuite

class AsciiGridTest extends FunSuite {

  val squareGrid: AsciiGrid = AsciiGrid(Array(Array(
    AsciiPixel('a'),
    AsciiPixel('b')),
    Array(AsciiPixel('c'),
      AsciiPixel('d'))))

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
    val newSquareGrid: AsciiGrid = AsciiGrid(Array.ofDim[AsciiPixel](height, width))

    newSquareGrid.setItemOnPos(0, 0, AsciiPixel('a'))
    newSquareGrid.setItemOnPos(0, 1, AsciiPixel('b'))
    newSquareGrid.setItemOnPos(1, 0, AsciiPixel('c'))
    newSquareGrid.setItemOnPos(1, 1, AsciiPixel('d'))

    assert(newSquareGrid.getItemOnPos(0, 0) == AsciiPixel('a'))
    assert(newSquareGrid.getItemOnPos(0, 1) == AsciiPixel('b'))
    assert(newSquareGrid.getItemOnPos(1, 0) == AsciiPixel('c'))
    assert(newSquareGrid.getItemOnPos(1, 1) == AsciiPixel('d'))
  }

  test("Set item o pos (out of bounds)") {
    val height = 1
    val width = 1
    val newSquareGrid: AsciiGrid = AsciiGrid(Array.ofDim[AsciiPixel](height, width))

    val caught =
      intercept[Exception] {
        newSquareGrid.setItemOnPos(0, 1, AsciiPixel('b'))
      }
    assert(caught.getMessage == "Invalid coordinates")
  }

  // ------------------------------------------------------------
  // GET ITEM ON POS

  test("Get item o pos (in bounds)") {
    assert(squareGrid.getItemOnPos(0, 0) == AsciiPixel('a'))
    assert(squareGrid.getItemOnPos(0, 1) == AsciiPixel('b'))
    assert(squareGrid.getItemOnPos(1, 0) == AsciiPixel('c'))
    assert(squareGrid.getItemOnPos(1, 1) == AsciiPixel('d'))
  }

  test("Get item o pos (out of bounds)") {
    val caught =
      intercept[Exception] {
        squareGrid.getItemOnPos(0, 2) == AsciiPixel('a')
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

  test("") {
    squareGrid.iterator.hasNext
  }
}
