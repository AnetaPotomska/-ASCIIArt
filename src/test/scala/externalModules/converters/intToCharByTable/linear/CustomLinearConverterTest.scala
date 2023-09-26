package externalModules.converters.intToCharByTable.linear

import org.scalatest.FunSuite

class CustomLinearConverterTest extends FunSuite {
  def convert(chars: String, item: Int): Option[Char] = new CustomLinearConverter(chars).convert(item)

  // ------------------------------------------------------------
  // CHARACTERS LENGTH

  test("Input characters length is larger than 255") {
    val chars = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
    assert(convert(chars, 0).isEmpty)
    assert(convert(chars, 25).isEmpty)
    assert(convert(chars, 255).isEmpty)
  }

  test("Input characters length is 0") {
    val chars = ""
    assert(convert(chars, 0).isEmpty)
    assert(convert(chars, 25).isEmpty)
    assert(convert(chars, 255).isEmpty)
  }

  // ------------------------------------------------------------
  // ITEM OUT OF BOUNDS

  test("Item is more than 255") {
    val chars = "abcdefgh"
    assert(convert(chars, 256).get == 'h')
    assert(convert(chars, 512).get == 'h')
    assert(convert(chars, 1024).get == 'h')
  }

  test("Item is less than 0") {
    val chars = "abcdefgh"
    assert(convert(chars, -1).get == 'a')
    assert(convert(chars, -58).get == 'a')
    assert(convert(chars, -1024).get == 'a')
  }

  // ------------------------------------------------------------
  // ITEM IN BOUNDS

  test("Item is between 0 - 255") {
    val chars = "abcdefgh"
    assert(convert(chars, 0).get == 'a')
    assert(convert(chars, 20).get == 'a')
    assert(convert(chars, 80).get == 'c')
    assert(convert(chars, 120).get == 'd')
    assert(convert(chars, 200).get == 'g')
    assert(convert(chars, 255).get == 'h')
    assert(convert(chars, 500).get == 'h')
  }
}
