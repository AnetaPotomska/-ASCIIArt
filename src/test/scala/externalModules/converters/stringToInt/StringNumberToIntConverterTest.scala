package externalModules.converters.stringToInt

import org.scalatest.FunSuite

class StringNumberToIntConverterTest extends FunSuite {
  def convert(item: String): Int = new StringNumberToIntConverter().convert(item)

  // ------------------------------------------------------------
  // POSITIVE

  test("Convert positive num string to int") {
    assert(convert("50") == 50)
  }

  // ------------------------------------------------------------
  // NEGATIVE

  test("Convert negative num string to int") {
    assert(convert("-50") == -50)
  }

  // ------------------------------------------------------------
  // STRING

  test("Convert non-number string") {
    val caught =
      intercept[Exception] {
        convert("50asd")
      }
    assert(caught.getMessage == "Given value isn't number")
  }

  test("Convert non-number string starting as negative") {
    val caught =
      intercept[Exception] {
        convert("-50asd")
      }
    assert(caught.getMessage == "Given value isn't number")
  }
}
