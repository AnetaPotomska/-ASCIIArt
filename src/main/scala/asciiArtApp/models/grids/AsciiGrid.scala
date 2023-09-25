package asciiArtApp.models.grids

import asciiArtApp.models.pixels.AsciiPixel

case class AsciiGrid(private val items: Array[Array[AsciiPixel]]) extends Grid[AsciiPixel] {
  override def getHeight: Int = items.length

  override def getWidth: Int = {
    if (getHeight <= 0) {
      return 0
    }
    items.head.length
  }

  override def getItemOnPos(x: Int, y: Int): AsciiPixel = {
    if (!checkCoordination(x, y)) {
      throw new IllegalArgumentException("Invalid coordinates")
    }
    items(x)(y)
  }

  override def setItemOnPos(x: Int, y: Int, item: AsciiPixel): Unit = {
    items(x)(y) = item
  }
}
