package asciiArtApp.models.grids

import asciiArtApp.models.pixels.{GreyscalePixel, Pixel, RGBPixel}

case class RGBGrid(items: Array[Array[RGBPixel]]) extends Grid[RGBPixel] {

}
