package externalModules.converters.stringToInt

import org.scalatest.FunSuite

class StringNumberToIntConverterTest extends FunSuite {
  def convert(item: String): Option[Int] = new StringNumberToIntConverter().convert(item)

  // ------------------------------------------------------------
  // POSITIVE

  test("Convert positive num string to int") {
    assert(convert("+50").get == 50)
  }

  test("Convert positive num string starting with + to int") {
    assert(convert("+50").get == 50)
  }

  // ------------------------------------------------------------
  // NEGATIVE

  test("Convert negative num string to int") {
    assert(convert("-50").get == -50)
  }

  // ------------------------------------------------------------
  // ZERO

  test("Convert 0 num string to int") {
    assert(convert("+0").get == 0)
    assert(convert("-0").get == 0)
    assert(convert("0").get == 0)
  }

  // ------------------------------------------------------------
  // STRING

  test("Convert non-number string") {
    assert(convert("50asd").isEmpty)
  }

  test("Convert non-number string starting as negative") {
    assert(convert("-50asd").isEmpty)
  }

  test("Convert non-number string starting only as negative") {
    assert(convert("-").isEmpty)
  }

  test("Convert non-number string starting only as positive") {
    assert(convert("+").isEmpty)
  }

  test("Convert empty string") {
    assert(convert("").isEmpty)
  }
}
