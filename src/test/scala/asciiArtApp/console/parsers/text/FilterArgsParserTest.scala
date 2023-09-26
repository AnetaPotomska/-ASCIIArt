package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.flip.axis.{FlipXAxisFilter, FlipYAxisFilter}
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness.BrightnessFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert.InvertFilter
import org.scalatest.FunSuite

class FilterArgsParserTest extends FunSuite {
  def parse(source: Array[String]): Seq[GreyscaleImageFilter] = new FilterArgsParser().parse(source)

  // ------------------------------------------------------------
  // NO ARG

  test("No filter args") {
    val source = Array("bla", "blabla")
    val filters = parse(source)
    assert(filters.isEmpty)
  }

  // ------------------------------------------------------------
  // INVERT

  test("Invert filter arg") {
    val source = Array("bla", "--invert")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[InvertFilter])
  }

  // ------------------------------------------------------------
  // FLIP

  test("Flip x filter arg") {
    val source = Array("bla", "--flip", "x")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[FlipXAxisFilter])
  }

  test("Flip y filter arg") {
    val source = Array("bla", "--flip", "y")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[FlipYAxisFilter])
  }

  test("Flip filter without arg") {
    val source = Array("bla", "--flip")
    var filters = Seq[GreyscaleImageFilter]()
    val caught =
      intercept[Exception] {
        filters = parse(source)
      }
    assert(caught.getMessage == "Missing flip value")
    assert(filters.isEmpty)
  }

  test("Flip filter without known arg") {
    val source = Array("bla", "--flip", "b")
    var filters = Seq[GreyscaleImageFilter]()
    val caught =
      intercept[Exception] {
        filters = parse(source)
      }
    assert(caught.getMessage == "Unknown flip value")
    assert(filters.isEmpty)
  }

  // ------------------------------------------------------------
  // BRIGHTNESS

  test("Brightness filter with arg in format num") {
    val source = Array("bla", "--brightness", "50")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[BrightnessFilter])
  }

  test("Brightness filter with arg in format +num") {
    val source = Array("bla", "--brightness", "550")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[BrightnessFilter])
  }

  test("Brightness filter with arg in format -num") {
    val source = Array("bla", "--brightness", "-50")
    val filters = parse(source)
    assert(filters.length == 1)
    assert(filters(0).isInstanceOf[BrightnessFilter])
  }

  test("Brightness filter with non-numeric-arg") {
    val source = Array("bla", "--brightness", "n0")
    var filters = Seq[GreyscaleImageFilter]()
    val caught =
      intercept[Exception] {
        filters = parse(source)
      }
    assert(caught.getMessage == "Given value isn't number")
    assert(filters.isEmpty)
  }

  test("Brightness filter without arg") {
    val source = Array("bla", "--brightness")
    var filters = Seq[GreyscaleImageFilter]()
    val caught =
      intercept[Exception] {
        filters = parse(source)
      }
    assert(caught.getMessage == "Missing brightness value")
    assert(filters.isEmpty)
  }

  // ------------------------------------------------------------
  // COMBINATION

  test("All filters args") {
    val source = Array("--invert", "--flip", "x", "--flip", "y", "--brightness", "50")
    val filters = parse(source)
    assert(filters.length == 4)
    assert(filters(0).isInstanceOf[InvertFilter])
    assert(filters(1).isInstanceOf[FlipXAxisFilter])
    assert(filters(2).isInstanceOf[FlipYAxisFilter])
    assert(filters(3).isInstanceOf[BrightnessFilter])
  }
}
