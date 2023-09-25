package helpers;
import scala.util.Random

class CustomRandom(seed: Long) extends Random {
  private val random = new Random(seed)

  override def nextInt(max: Int): Int = random.nextInt(max)

  def nextInt(min: Int, max: Int): Int = {
    min + random.nextInt(max - min + 1)
  }
}