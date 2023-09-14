package asciiArtApp.console.parsers.text

import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.converters.intToCharByTable.linear.{CustomLinearConverter, PaulBourkesConverter}
import externalModules.converters.intToCharByTable.nonLinear.FunNonLinearConverter
import org.scalatest.FunSuite

class TableArgParserTest extends FunSuite {
  def parse (source: Array[String]): IntToCharConverter = new TableArgParser().parse(source)

  // ------------------------------------------------------------
  // NO ARG
  test("No table args") {
    val source = Array("bla", "blabla")
    val table = parse(source)
    assert(table.isInstanceOf[PaulBourkesConverter])
  }

  // ------------------------------------------------------------
  // TOO MANY ARGS
  test("Too many table name args") {
    val source = Array("--table", "name1", "--table", "name2")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many table sources")
  }

  test("Too many custom table args") {
    val source = Array("--custom-table", "--custom-table")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many table sources")
  }

  test("Too many table args in general") {
    val source = Array("--table", "name1", "--custom-table")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Found too many table sources")
  }

  // ------------------------------------------------------------
  // TABLE WITH NAME ARG

  test("Table with name \"bourke\"") {
    val source = Array("--table", "bourke")
    val table = parse(source)
    assert(table.isInstanceOf[PaulBourkesConverter])
  }

  test("Table with name \"fun\"") {
    val source = Array("--table", "fun")
    val table = parse(source)
    assert(table.isInstanceOf[FunNonLinearConverter])
  }

  test("Table without name") {
    val source = Array("--table")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No table name was found")
  }

  test("Table with not-defined name") {
    val source = Array("--table", "someRandomName")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Invalid table name")
  }


  // ------------------------------------------------------------
  // CUSTOM TABLE ARG

  test("Custom table with characters") {
    val source = Array("--custom-table", " .,_-olpQR")
    val table = parse(source)
    assert(table.isInstanceOf[CustomLinearConverter])
  }

  test("Custom table without characters") {
    val source = Array("--custom-table")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "No characters for table were found")
  }

  test("Custom table with empty characters") {
    val source = Array("--custom-table", "")
    val caught =
      intercept[Exception] {
        parse(source)
      }
    assert(caught.getMessage == "Empty characters for table")
  }
}
