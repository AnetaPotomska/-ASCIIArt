package asciiArtApp.console.parsers.text

import externalModules.exporters.text.{FileOutputExporter, StdOutputExporter, TextExporter}
import org.scalatest.FunSuite

import java.io.File

class ExporterArgsParserTest extends FunSuite {
  def parse (source: Array[String]): Seq[TextExporter] = new ExporterArgsParser().parse(source)

  // ------------------------------------------------------------
  // NO ARG

  test("No export args") {
    val source = Array("bla", "blabla")
    var exporters = Seq[TextExporter]()
    val caught =
      intercept[Exception] {
        exporters = parse(source)
      }
    assert(caught.getMessage == "No export command was found")
    assert(exporters.isEmpty)
  }

  // ------------------------------------------------------------
  // ONE OUTPUT CONSOLE ARG

  test("Output console arg") {
    val source = Array("--output-console", "blabla")
    val exporters = parse(source)
    assert(exporters.length == 1)
    assert(exporters(0).isInstanceOf[StdOutputExporter])
  }

  // ------------------------------------------------------------
  // ONE OUTPUT FILE ARG

  test("Output file arg with path") {
    val source = Array("--output-file", "blabla")
    val exporters = parse(source)
    assert(exporters.length == 1)
    assert(exporters(0).isInstanceOf[FileOutputExporter])
    new File("blabla").delete()
  }

  test("Output file arg without path") {
    val source = Array("blabla", "--output-file")
    var exporters = Seq[TextExporter]()
    val caught =
      intercept[Exception] {
        exporters = parse(source)
      }
    assert(caught.getMessage == "Missing path for export")
    assert(exporters.isEmpty)
  }

  // ------------------------------------------------------------
  // MULTIPLE OUTPUT ARGS

  test("Output console and file args (2)") {
    val source = Array("--output-file", "blabla", "--output-console")
    val exporters = parse(source)
    assert(exporters.length == 2)
    assert(exporters(0).isInstanceOf[FileOutputExporter])
    assert(exporters(1).isInstanceOf[StdOutputExporter])
    new File("blabla").delete()
  }

  test("Output console and file args (4)") {
    val source = Array("--output-file", "blabla", "--output-console", "--output-file", "blabla", "--output-console")
    val exporters = parse(source)
    assert(exporters.length == 4)
    assert(exporters(0).isInstanceOf[FileOutputExporter])
    assert(exporters(1).isInstanceOf[StdOutputExporter])
    assert(exporters(2).isInstanceOf[FileOutputExporter])
    assert(exporters(3).isInstanceOf[StdOutputExporter])
    new File("blabla").delete()
  }
}
