package asciiArtApp.models.images

import asciiArtApp.models.grids.{GreyscaleGrid, Grid}
import asciiArtApp.models.pixels.GreyscalePixel

case class GreyscaleImage(private val grid: GreyscaleGrid) extends Image[GreyscalePixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): GreyscalePixel = grid.getItemOnPos(x, y)

  override def setItemOnPos(x: Int, y: Int, item: GreyscalePixel): Unit = {
    grid.setItemOnPos(x, y, item)
  }

  override def getGridCopy: Grid[GreyscalePixel] = {
    val array = Array.ofDim[GreyscalePixel](getHeight, getWidth)
    val newGrid = GreyscaleGrid(array)
    for ((x, y) <- grid) {
      newGrid.setItemOnPos(x, y, grid.getItemOnPos(x, y))
    }
    newGrid
  }

  override def iterator: Iterator[(Int, Int)] = {
    grid.iterator
  }
}