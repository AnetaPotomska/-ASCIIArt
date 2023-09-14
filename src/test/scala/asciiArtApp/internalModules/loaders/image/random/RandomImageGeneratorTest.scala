package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.images.RGBImage
import org.scalatest.FunSuite

class RandomImageGeneratorTest extends FunSuite {
  def load(): RGBImage = new RandomImageGenerator().load()

  // there is small chance this test will fail due to randomness
  test("Random") {
    assert(load() != load())
  }
}
