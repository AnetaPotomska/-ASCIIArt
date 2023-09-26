package asciiArtApp.console.parsers.text

import externalModules.exporters.text.{FileOutputExporter, StdOutputExporter, TextExporter}

import java.io.File

class ExportArgsParser() extends TextParser[Seq[TextExporter]] {
  override def parse(source: Array[String]): Seq[TextExporter] = {
    // get number of arguments that are output-related
    val outputConsoleArgCnt = source.count(_.equals("--output-console"))
    val outputFileArgCnt = source.count(_.equals("--output-file"))

    // check count of arguments (atleast one argument has to be found)
    if (outputConsoleArgCnt == 0 && outputFileArgCnt == 0) {
      throw new Exception("No export command was found")
    }

    // go through source and look for output-related arguments
    var toRet = Seq[TextExporter]()
    for (s <- 0 until source.length) {
      source(s) match {
        case "--output-console" => toRet = toRet.appended(new StdOutputExporter)
        // look one ahead for parameter of this argument
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