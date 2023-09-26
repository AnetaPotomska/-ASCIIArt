package externalModules.converters.intToCharByTable.linear

import org.scalatest.FunSuite

class PaulBourkesConverterTest extends FunSuite {
  def convert(item: Int): Option[Char] = new PaulBourkesConverter().convert(item)

  // ------------------------------------------------------------
  // CHARACTERS LENGTH

  test("Input characters length is in bounds of 1 - 255") {
    assert(convert(-1).isDefined)
    assert(convert(0).isDefined)
    assert(convert(25).isDefined)
    assert(convert(255).isDefined)
    assert(convert(256).isDefined)
  }

  // ------------------------------------------------------------
  // ITEM OUT OF BOUNDS

  test("Item is more than 255") {
    assert(convert(256).get == ' ')
    assert(convert(512).get == ' ')
    assert(convert(1024).get == ' ')
  }

  test("Item is less than 0") {
    val chars = "abcdefgh"
    assert(convert(-1).get == '$')
    assert(convert(-58).get == '$')
    assert(convert(-1024).get == '$')
  }

  // ------------------------------------------------------------
  // ITEM IN BOUNDS

  test("Item is between 0 - 255") {
    assert(convert(0).get == '$')
    assert(convert(20).get == '&')
    assert(convert(80).get == 'O')
    assert(convert(120).get == 'v')
    assert(convert(200).get == '~')
    assert(convert(255).get == ' ')
  }
}
