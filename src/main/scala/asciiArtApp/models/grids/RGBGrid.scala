package asciiArtApp.models.grids

import asciiArtApp.models.pixels.RGBPixel

case class RGBGrid(private val items: Array[Array[RGBPixel]]) extends Grid[RGBPixel] {
  override def getHeight: Int = items.length

  override def getWidth: Int = {
    if (getHeight <= 0) {
      return 0
    }
    items.head.length
  }

  override def getItemOnPos(x: Int, y: Int): RGBPixel = {
    if(!checkCoordination(x, y)) {
      throw new IllegalArgumentException("Invalid coordinates")
    }
    items(x)(y)
  }
}
