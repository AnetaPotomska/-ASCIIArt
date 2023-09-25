package asciiArtApp.models.images

import asciiArtApp.models.grids.Grid
import asciiArtApp.models.pixels.Pixel

trait Image[T <: Pixel] extends Iterable[(Int, Int)] {
  def getHeight: Int

  def getWidth: Int

  def getItemOnPos(x: Int, y: Int): T

  def setItemOnPos(x: Int, y: Int, item: T): Unit

  def getGridCopy: Grid[T]

  def iterator: Iterator[(Int, Int)]

  def foreach[U](f: (Int, Int) => U): Unit = {
    for (coordinates <- this.iterator) {
      f(coordinates._1, coordinates._2)
    }
  }
}