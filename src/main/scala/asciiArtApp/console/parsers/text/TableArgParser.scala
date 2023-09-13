package asciiArtApp.console.parsers.text

import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.converters.intToCharByTable.linear.{CustomLinearConverter, PaulBourkesConverter}
import externalModules.converters.intToCharByTable.nonLinear.FunNonLinearConverter

case class TableArgParser() extends TextParser[IntToCharConverter] {
  override def parse(source: Array[String]): IntToCharConverter = {
    val tableArgCnt = source.count(_.equals("--table "))
    val customTableArgCnt = source.count(_.equals("--custom-table"))

    if(tableArgCnt + customTableArgCnt > 1) {
      throw new Exception("Found too many table sources")
    }
    else if(tableArgCnt == 1) {
      val nameIndex = source.indexOf("--table") + 1
      if (nameIndex >= source.length) {
        throw new Exception("No table name was found")
      }
      val name = source(nameIndex)
      name match {
        case "bourke" => return new PaulBourkesConverter
        case "fun" => return new FunNonLinearConverter
        case _ =>
      }
    }
    else if(customTableArgCnt == 1) {
      val charsIndex = source.indexOf("--custom-table") + 1
      if (charsIndex >= source.length) {
        throw new Exception("No characters for table was found")
      }
      val chars = source(charsIndex)
      return new CustomLinearConverter(chars)
    }
    new PaulBourkesConverter
  }
}