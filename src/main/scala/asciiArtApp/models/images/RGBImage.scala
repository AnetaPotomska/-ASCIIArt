package asciiArtApp.models.images

import asciiArtApp.models.grids.{Grid, RGBGrid}
import asciiArtApp.models.pixels.RGBPixel

case class RGBImage(private val grid: RGBGrid) extends Image[RGBPixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): RGBPixel = grid.getItemOnPos(x, y)

  override def setItemOnPos(x: Int, y: Int, item: RGBPixel): Unit = {
    grid.setItemOnPos(x, y, item)
  }

  override def getGridCopy: Grid[RGBPixel] = {
    val array = Array.ofDim[RGBPixel](getHeight, getWidth)
    val newGrid = RGBGrid(array)
    for ((x, y) <- grid) {
      newGrid.setItemOnPos(x, y, grid.getItemOnPos(x, y))
    }
    newGrid
  }

  override def iterator: Iterator[(Int, Int)] = {
    grid.iterator
  }
}
