package externalModules.exporters.text

import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class StreamTextExporterTest extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    exporter.export("bla")
    assert(stream.toString("UTF-8") == "bla")
  }
}
