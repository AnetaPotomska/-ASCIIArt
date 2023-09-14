package externalModules.converters.intToCharByTable.linear

import org.scalatest.FunSuite

class CustomLinearConverterTest extends FunSuite {
  def convert(chars: String, item: Int): Char = new CustomLinearConverter(chars).convert(item)

  test("Check distribution") {
    val chars = "abcdefgh"
    assert(convert(chars, 0) == 'a')
    assert(convert(chars, 20) == 'a')
    assert(convert(chars, 80) == 'c')
    assert(convert(chars, 120) == 'd')
    assert(convert(chars, 200) == 'g')
    assert(convert(chars, 255) == 'h')
    assert(convert(chars, 500) == 'h')
  }
}
