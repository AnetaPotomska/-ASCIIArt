package asciiArtApp.models.grids

import asciiArtApp.models.images.Image
import asciiArtApp.models.pixels.Pixel

trait Grid[T <: Pixel] {
  def items: Array[Array[T]]

  def getHeight: Int = items.length

  def getWidth: Int = items.head.length

  def getItemOnPos(x: Int, y: Int): T = items(x)(y)

  def setItemOnPos(x: Int, y: Int, item: T): Unit = items(x)(y) = item
}
