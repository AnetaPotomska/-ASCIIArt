package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.internalModules.loaders.image.fromFile.{JpgLoader, PngLoader}
import asciiArtApp.internalModules.loaders.image.random.RandomImageGenerator
import org.mockito.MockitoSugar.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

import java.io.File

class ImageArgParserTest extends FunSuite {
  def parse (source: Array[String]): RGBImageLoader = new ImageArgParser().parse(source)

  // ------------------------------------------------------------
  // NO ARG

  test("No image args") {
    val source = Array("bla", "blabla")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No image source was found")
  }

  // ------------------------------------------------------------
  // TOO MANY ARGS

  test("Too many path image args") {
    val source = Array("--image", "path1", "--image", "path2")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many image sources")
  }

  test("Too many random image args") {
    val source = Array("--image-random", "--image-random")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many image sources")
  }

  test("Too many image args in general") {
    val source = Array("--image-random", "--image", "path")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many image sources")
  }

  // ------------------------------------------------------------
  // PATH IMAGE ARG

  test("Image with existing path and jpg extension") {
    val source = Array("--image", "src/test/resources/somePic.jpg")
    val image = parse(source)
    assert(image.isInstanceOf[JpgLoader])
  }

  test("Image with existing path and png extension") {
    val source = Array("--image", "src/test/resources/somePic.png")
    val image = parse(source)
    assert(image.isInstanceOf[PngLoader])
  }

  test("Image missing path arg") {
    val source = Array("--image")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No image path was found")
  }

  test("Image with path arg missing extension (no dot)") {
    val source = Array("--image", "path")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No extension found in path to image")
  }

  test("Image with path arg missing extension (with dot)") {
    val source = Array("--image", "path.")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No extension found in path to image")
  }

  test("Image with non-existing path") {
    val mockFile = mock[File]
    val source = Array("--image", "iDontExist.png")

    when(mockFile.exists()).thenReturn(false)
    when(mockFile.canRead).thenReturn(true)

    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Couldn't load file with image, file doesn't exist")
  }

  test("Image with existing path but with non-existing extension") {
    val source = Array("--image", "src/test/resources/iDoExist.nonExistingExtension")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Not supported extension for image")
  }

  // ------------------------------------------------------------
  // RANDOM IMAGE ARG

  test("Random image arg (as last)") {
    val source = Array("--image-random")
    val image = parse(source)
    assert(image.isInstanceOf[RandomImageGenerator])
  }

  test("Random image arg (not last)") {
    val source = Array("--image-random", "other")
    val image = parse(source)
    assert(image.isInstanceOf[RandomImageGenerator])
  }
}
