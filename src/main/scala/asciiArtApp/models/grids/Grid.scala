package asciiArtApp.models.grids

import asciiArtApp.models.pixels.Pixel

trait Grid[T <: Pixel] {
  def getHeight: Int

  def getWidth: Int

  def getItemOnPos(x: Int, y: Int): T

  def checkCoordination(x: Int, y: Int): Boolean = {
    x >= 0 && x < getHeight && y >= 0 && y < getWidth
  }
}
