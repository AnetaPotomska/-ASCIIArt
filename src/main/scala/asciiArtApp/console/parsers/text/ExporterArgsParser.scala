package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.exporters.asciiImage.{AsciiImageExporter, FileOutputExporter, StdOutputExporter}

import java.io.File

case class ExporterArgsParser() extends TextParser[Seq[AsciiImageExporter]] {
  override def parse(source: Array[String]): Seq[AsciiImageExporter] = {
    val outputConsoleArgCnt = source.count(_.equals("--output-console"))
    val outputFileArgCnt = source.count(_.equals("--output-file"))

    if (outputConsoleArgCnt == 0 && outputFileArgCnt == 0) {
      throw new Exception("No export command was found")
    }
    var toRet = Seq[AsciiImageExporter]()
    for (s <- 0 until source.length) {
      source(s) match {
        case "--output-console" => toRet = toRet.appended(new StdOutputExporter)
        case "--output-file" => {
          if (s + 1 >= source.length) {
            throw new Exception("Missing path for export")
          }
          val path = source(s + 1)
          val file = new File(path)
          toRet = toRet.appended(new FileOutputExporter(file))
        }
        case _ =>
      }
    }
    toRet
  }
}