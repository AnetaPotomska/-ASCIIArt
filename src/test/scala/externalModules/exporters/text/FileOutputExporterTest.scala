package externalModules.exporters.text

import helpers.TestWithFiles
import org.scalatest.FunSuite

import java.io.File

class FileOutputExporterTest extends FunSuite with TestWithFiles {
  test("No file exists") {
    val fileName = getTestFile

    try {
      ensureDeleted(fileName)

      val file = new File(fileName)
      val exporter = new FileOutputExporter(file)

      exporter.export("bla")
      exporter.close()

      assertFileContent(fileName, "bla")
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("File already exists") {
    val fileName = getTestFile

    try {
      ensureCreated(fileName)

      val file = new File(fileName)
      val exporter = new FileOutputExporter(file)

      exporter.export("bla")
      exporter.close()

      assertFileContent(fileName, "bla")
    }
    finally {
      ensureDeleted(fileName)
    }
  }
}
