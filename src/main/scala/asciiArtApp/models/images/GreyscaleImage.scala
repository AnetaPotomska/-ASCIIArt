package asciiArtApp.models.images

import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.pixels.GreyscalePixel

case class GreyscaleImage(private val grid: GreyscaleGrid) extends Image[GreyscalePixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): GreyscalePixel = grid.getItemOnPos(x, y)
}