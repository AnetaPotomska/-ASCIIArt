package asciiArtApp.models.grids

import asciiArtApp.models.pixels.Pixel

trait Grid[T <: Pixel] extends Iterable[(Int, Int)] {
  // ---------------------------------------------------------
  // BASIC INTERFACE
  def getHeight: Int

  def getWidth: Int

  def getItemOnPos(x: Int, y: Int): T

  def setItemOnPos(x: Int, y: Int, item: T): Unit

  def checkCoordination(x: Int, y: Int): Boolean

  // ---------------------------------------------------------
  // ITERATOR & FOREACH
  def iterator: Iterator[(Int, Int)] = new Iterator[(Int, Int)] {
    private var row = 0
    private var col = 0

    override def hasNext: Boolean = row < getHeight

    override def next(): (Int, Int) = {
      if (!hasNext) {
        throw new Exception("No more elements in the grid")
      }

      val coordinates = (row, col)
      col += 1
      if (col >= getWidth) {
        col = 0
        row += 1
      }
      coordinates
    }
  }

  def foreach[U](f: (Int, Int) => U): Unit = {
    for (coordinates <- this.iterator) {
      f(coordinates._1, coordinates._2)
    }
  }
}
