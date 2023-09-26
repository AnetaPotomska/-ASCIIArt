package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.models.images.RGBImage
import helpers.TestWithFiles
import org.scalatest.FunSuite

import java.io.File

class JpgLoaderTest extends FunSuite with TestWithFiles  {
  val path = "src/test/resources/somePic.jpg"
  val file = new File(path)
  val image: Option[RGBImage] = new JpgLoader(file).load()

  // ------------------------------------------------------------
  // LOAD EXISTING FILE

  test("Check size of loaded jpg picture") {
    assert(image.isDefined)
    assert(image.get.getHeight == 2)
    assert(image.get.getWidth == 2)
  }
}