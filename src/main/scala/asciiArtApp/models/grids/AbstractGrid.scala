package asciiArtApp.models.grids

import asciiArtApp.models.pixels.Pixel

abstract class AbstractGrid[T <: Pixel](private val items: Array[Array[T]]) extends Grid[T] {
  override def getHeight: Int = items.length

  override def getWidth: Int = {
    if (getHeight <= 0) {
      return 0
    }
    items.head.length
  }

  override def setItemOnPos(x: Int, y: Int, item: T): Unit = {
    if (!checkCoordination(x, y)) {
      throw new IllegalArgumentException("Invalid coordinates")
    }
    items(x)(y) = item
  }

  override def getItemOnPos(x: Int, y: Int): T = {
    if (!checkCoordination(x, y)) {
      throw new IllegalArgumentException("Invalid coordinates")
    }
    items(x)(y)
  }

  private def checkCoordination(x: Int, y: Int): Boolean = {
    x >= 0 && x < getHeight && y >= 0 && y < getWidth
  }
}
