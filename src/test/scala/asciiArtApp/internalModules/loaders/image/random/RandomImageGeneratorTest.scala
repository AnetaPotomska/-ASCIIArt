package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.models.images.RGBImage
import helpers.CustomRandom
import org.mockito.Mockito.when
import org.scalatest.FunSuite

class RandomImageGeneratorTest extends FunSuite {
  val customRandom = new CustomRandom(123L)
  val minImageHeight = 1
  val maxImageHeight = 2
  val minImageWidth = 1
  val maxImageWidth = 4
  def load(): RGBImage = new RandomImageGenerator(customRandom, minImageHeight, maxImageHeight, minImageWidth, maxImageWidth).load()

  test("Check size (same height and width)") {
    when(customRandom.nextInt(minImageHeight, maxImageHeight)).thenReturn(2)
    when(customRandom.nextInt(minImageWidth, maxImageWidth)).thenReturn(3)

    val result1 = load()
    val result2 = load()

    assert(result1 == result2)
  }
}
