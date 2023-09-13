package asciiArtApp.models.images

import asciiArtApp.models.grids.Grid
import asciiArtApp.models.pixels.Pixel

trait Image[T <: Pixel] {
  def grid: Grid[T]

  def getHeight: Int = grid.getHeight

  def getWidth: Int = grid.getWidth

  def getItemOnPos(x: Int, y: Int): T = grid.getItemOnPos(x, y)
}