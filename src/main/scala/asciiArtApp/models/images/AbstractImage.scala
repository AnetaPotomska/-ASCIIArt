package asciiArtApp.models.images

import asciiArtApp.models.grids.Grid
import asciiArtApp.models.pixels.Pixel

abstract class AbstractImage[T <: Pixel](private val grid: Grid[T]) extends Image[T] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): T = grid.getItemOnPos(x, y)

  override def setItemOnPos(x: Int, y: Int, item: T): Unit = grid.setItemOnPos(x, y, item)

  override def checkCoordination(x: Int, y: Int): Boolean = grid.checkCoordination(x, y)

  override def iterator: Iterator[(Int, Int)] = grid.iterator
}
