package asciiArtApp.models.images

import asciiArtApp.models.grids.Grid
import asciiArtApp.models.pixels.Pixel

trait Image[T <: Pixel] {
  def getHeight: Int

  def getWidth: Int

  def getItemOnPos(x: Int, y: Int): T
}