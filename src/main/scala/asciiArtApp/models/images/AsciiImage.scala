package asciiArtApp.models.images

import asciiArtApp.models.grids.AsciiGrid
import asciiArtApp.models.pixels.AsciiPixel

case class AsciiImage(private val grid: AsciiGrid) extends Image[AsciiPixel] {
  override def getHeight: Int = grid.getHeight

  override def getWidth: Int = grid.getWidth

  override def getItemOnPos(x: Int, y: Int): AsciiPixel = grid.getItemOnPos(x, y)
}
