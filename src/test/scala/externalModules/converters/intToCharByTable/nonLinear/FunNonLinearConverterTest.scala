package externalModules.converters.intToCharByTable.nonLinear

import org.scalatest.FunSuite

class FunNonLinearConverterTest extends FunSuite {

  // ------------------------------------------------------------
  // CHARACTERS LENGTH

  test("Input characters length is less than 10") {
    val chars = "abcdefgh"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)
    assert(convert(-1).isEmpty)
    assert(convert(0).isEmpty)
    assert(convert(25).isEmpty)
    assert(convert(255).isEmpty)
    assert(convert(256).isEmpty)
  }

  test("Input characters length is more than 9 (letters from 10th position will not be used)") {
    val chars = "abcdefghij"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(-14141).get == 'j')
    assert(convert(-1).get == 'j')
    assert(convert(0).get == 'j')
    assert(convert(25).get == 'j')
    assert(convert(255).get == 'a')
    assert(convert(256).get == 'a')
    assert(convert(24468).get == 'a')
  }

  // ------------------------------------------------------------
  // ITEM DISTRIBUTION

  test("Convert 0") {
    val chars = "abcdefghij"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(0).get == 'j')
  }

  test("Convert 255") {
    val chars = "abcdefghij"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(255).get == 'a')
  }

  test("Convert less than 0") {
    val chars = "abcdefghij"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(-14141).get == 'j')
    assert(convert(-180).get == 'j')
    assert(convert(-1).get == 'j')
  }

  test("Convert more than 255") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(256).get == 'a')
    assert(convert(2448).get == 'a')
    assert(convert(24468).get == 'a')
  }

  // ------------------------------------------------------------
  // ITEM DISTRIBUTION - BASED ON CODE

  test("Convert more than 230") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(231).get == 'a')
    assert(convert(256).get == 'a')
    assert(convert(2448).get == 'a')
    assert(convert(24468).get == 'a')
  }

  test("Convert in bounds 205 - 230") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(205).get == 'b')
    assert(convert(218).get == 'b')
    assert(convert(230).get == 'b')
  }

  test("Convert in bounds 187 - 204") {
    val chars = "abcdefghij"
    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(187).get == 'c')
    assert(convert(196).get == 'c')
    assert(convert(204).get == 'c')
  }

  test("Convert int bounds 170 - 186") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(170).get == 'd')
    assert(convert(177).get == 'd')
    assert(convert(186).get == 'd')
  }

  test("Convert int bounds 153 - 169") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(153).get == 'e')
    assert(convert(160).get == 'e')
    assert(convert(169).get == 'e')
  }

  test("Convert int bounds 131 - 152") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(131).get == 'f')
    assert(convert(140).get == 'f')
    assert(convert(152).get == 'f')
  }

  test("Convert int bounds 112 - 129") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(112).get == 'g')
    assert(convert(125).get == 'g')
    assert(convert(129).get == 'g')
  }

  test("Convert int bounds 57 - 110") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(57).get == 'h')
    assert(convert(90).get == 'h')
    assert(convert(110).get == 'h')
  }

  test("Convert int bounds 32 - 56") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(32).get == 'i')
    assert(convert(40).get == 'i')
    assert(convert(56).get == 'i')
  }

  test("Convert int bounds negative - 31") {
    val chars = "abcdefghij"

    def convert(item: Int): Option[Char] = new FunNonLinearConverter(chars).convert(item)

    assert(convert(-150).get == 'j')
    assert(convert(0).get == 'j')
    assert(convert(31).get == 'j')
  }
}
