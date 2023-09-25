package asciiArtApp.models.grids

import asciiArtApp.models.pixels.GreyscalePixel
case class GreyscaleGrid(private val items: Array[Array[GreyscalePixel]]) extends Grid[GreyscalePixel] {
  override def getHeight: Int = items.length

  override def getWidth: Int = {
    if (getHeight <= 0) {
      return 0
    }
    items.head.length
  }

  override def getItemOnPos(x: Int, y: Int): GreyscalePixel = {
    if (!checkCoordination(x, y)) {
      throw new IllegalArgumentException("Invalid coordinates")
    }
    items(x)(y)
  }

  override def setItemOnPos(x: Int, y: Int, item: GreyscalePixel): Unit = {
    items(x)(y) = item
  }
}
