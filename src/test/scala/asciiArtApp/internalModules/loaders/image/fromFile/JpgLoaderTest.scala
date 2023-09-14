package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel
import org.scalatest.FunSuite

import java.io.File

class JpgLoaderTest extends FunSuite {
  val path = "src/test/resources/somePic.jpg"
  val file = new File(path)
  def load(): RGBImage = new JpgLoader(file).load()
  val image: RGBImage = load()

  test("Check size of loaded jpg picture") {
    assert(image.getHeight == 2)
    assert(image.getWidth == 2)
  }
}
