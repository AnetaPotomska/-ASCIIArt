package externalModules.converters.intToCharByTable.nonLinear

import org.scalatest.FunSuite

class FunNonLinearConverterTest extends FunSuite {
  def convert(item: Int): Char = new FunNonLinearConverter().convert(item)

  test("Convert 0") {
    assert(convert(0) == '@')
  }

  test("Convert 255") {
    assert(convert(255) == ' ')
  }
}
