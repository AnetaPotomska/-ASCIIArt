package asciiArtApp.models.images

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.pixels.RGBPixel

case class RGBImage(private val grid: RGBGrid) extends Image[RGBPixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): RGBPixel = grid.getItemOnPos(x, y)
}
