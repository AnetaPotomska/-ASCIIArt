package externalModules.converters.intToCharByTable.linear

import org.scalatest.FunSuite

class PaulBourkesConverterTest extends FunSuite {
  def convert(item: Int): Char = new PaulBourkesConverter().convert(item)

  test("Check distribution") {
    assert(convert(0) == '$')
    assert(convert(20) == '&')
    assert(convert(80) == 'O')
    assert(convert(120) == 'v')
    assert(convert(200) == '~')
    assert(convert(255) == ' ')
    assert(convert(500) == ' ')
  }
}
