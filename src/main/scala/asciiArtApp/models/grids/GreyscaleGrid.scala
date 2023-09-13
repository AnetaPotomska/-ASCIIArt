package asciiArtApp.models.grids
import asciiArtApp.models.pixels.{AsciiPixel, GreyscalePixel}


case class GreyscaleGrid(items: Array[Array[GreyscalePixel]]) extends Grid[GreyscalePixel] {

}
