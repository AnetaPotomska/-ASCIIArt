package asciiArtApp.internalModules.converters

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.{GreyscaleImage, RGBImage}
import asciiArtApp.models.pixels.{GreyscalePixel, RGBPixel}
import org.scalatest.FunSuite

class RGBToGreyscaleConverterTest extends FunSuite {
  def convert(item: RGBImage): GreyscaleImage = new RGBToGreyscaleConverter().convert(item)

  // ------------------------------------------------------------
  // SINGLE PIXEL ASCII IMAGE
  test("Single pixel rgb image to greyscale image") {
    val grid = Array.ofDim[RGBPixel](1, 1)
    grid(0)(0) = RGBPixel(0, 10, 20)
    val rgbImage = RGBImage(RGBGrid(grid))
    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.getItemOnPos(0, 0) == GreyscalePixel(8))
  }


  // ------------------------------------------------------------
  // BIG RGB IMAGE

  test("Rgb image to greyscale image") {
    val grid = Array.ofDim[RGBPixel](1, 2)
    grid(0)(0) = RGBPixel(0, 10, 20)
    grid(0)(1) = RGBPixel(32, 0, 0)
    val rgbImage = RGBImage(RGBGrid(grid))
    val greyscaleImage = convert(rgbImage)
    assert(greyscaleImage.getItemOnPos(0, 0) == GreyscalePixel(8))
    assert(greyscaleImage.getItemOnPos(0, 1) == GreyscalePixel(9))
  }
}
