package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.images.RGBImage
import org.mockito.Mockito.when
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.Random

class RandomImageGeneratorTest extends FunSuite {
  val mockRandom: Random = mock[Random]

  // ------------------------------------------------------------
  // CHECK BOUNDS

  test("Check bounds (all 0)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, 0, 0, 0, 0)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  test("Check bounds (some 0)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, 0, 5,8,9)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  test("Check bounds (all negative)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, -1, -1, -1, -1)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  test("Check bounds (some negative)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, 5, 10, -50,100)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  test("Check bounds (minWidth > maxWidth)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, 5, 20, 500, 100)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  test("Check bounds (minHeight > maxHeight)") {
    val randomImageGenerator = new RandomImageGenerator(mockRandom, 5, 2, 50, 100)

    val result = randomImageGenerator.load()
    assert(result.isEmpty)
  }

  // ------------------------------------------------------------
  // RANDOM IMAGE

  test("Should generate random image in given bounds") {
    val fixedSeedRandom = new Random(123)
    val randomImageGenerator = new RandomImageGenerator(fixedSeedRandom, 10, 20, 30, 40)

    val image = randomImageGenerator.load()
    assert(image.isDefined)

    assert(image.get.getHeight >= 10 && image.get.getHeight <= 20)
    assert(image.get.getWidth >= 30 && image.get.getWidth <= 40)

    for ((h, w) <- image.get) {
      val pixel = image.get.getItemOnPos(h, w)
      assert(pixel.red >= 0 && pixel.red <= 255)
      assert(pixel.green >= 0 && pixel.green <= 255)
      assert(pixel.blue >= 0 && pixel.blue <= 255)
    }
  }



}
