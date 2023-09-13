package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.{Image, RGBImage}
import asciiArtApp.models.pixels.RGBPixel

import scala.util.Random
class RandomImageGeneratorTest() extends FunSuite {
  test("Image is generated randomly") {
    val loader: RandomImageGenerator = RandomImageGenerator()
    assert(loader.load != loader.load)
  }
  test("Generated image is RGB image") {
    val loader: RandomImageGenerator = RandomImageGenerator()
    val image = loader.load()
    assert(image.isInstanceOf[RGBImage])
  }
}
