package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel
import org.scalatest.FunSuite

import java.io.File

class PngLoaderTest extends FunSuite {
  val path = "src/test/resources/somePic.png"
  val file = new File(path)

  def load(): RGBImage = new PngLoader(file).load()

  val image: RGBImage = load()

  test("Check size of loaded png picture") {
    assert(image.getHeight == 2)
    assert(image.getWidth == 2)
  }

  // I got the values from graphic program
  test("Check values of pixels of loaded png picture") {
    assert(image.getItemOnPos(0, 0) == RGBPixel(255, 255, 255))
    assert(image.getItemOnPos(0, 1) == RGBPixel(255, 1, 1))
    assert(image.getItemOnPos(1, 0) == RGBPixel(3, 3, 255))
    assert(image.getItemOnPos(1, 1) == RGBPixel(1, 255, 1))
  }

}
