package asciiArtApp.models.images

import asciiArtApp.models.grids.{AsciiGrid, Grid}
import asciiArtApp.models.pixels.AsciiPixel

case class AsciiImage(private val grid: AsciiGrid) extends Image[AsciiPixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): AsciiPixel = grid.getItemOnPos(x, y)

  override def setItemOnPos(x: Int, y: Int, item: AsciiPixel): Unit = {
    grid.setItemOnPos(x, y, item)
  }

  override def getGridCopy: Grid[AsciiPixel] = {
    val array = Array.ofDim[AsciiPixel](getHeight, getWidth)
    val newGrid = AsciiGrid(array)
    for((x, y) <- grid) {
      newGrid.setItemOnPos(x, y, grid.getItemOnPos(x, y))
    }
    newGrid
  }

  override def iterator: Iterator[(Int, Int)] = {
    grid.iterator
  }
}
