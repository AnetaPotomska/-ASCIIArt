package asciiArtApp.console.parsers.text

import externalModules.converters.intToCharByTable.IntToCharConverter
import externalModules.converters.intToCharByTable.linear.{CustomLinearConverter, PaulBourkesConverter}
import externalModules.converters.intToCharByTable.nonLinear.FunNonLinearConverter

class TableArgParser() extends TextParser[IntToCharConverter] {
  override def parse(source: Array[String]): IntToCharConverter = {
    // get count of table related args
    val tableArgCnt = source.count(_.equals("--table"))
    val customTableArgCnt = source.count(_.equals("--custom-table"))

    // there is support only for 1 or 0 table related arguments
    if(tableArgCnt + customTableArgCnt > 1) {
      throw new Exception("Found too many table sources")
    }

    // predefined table will be used based on name
    else if(tableArgCnt == 1) {
      // look one ahead for parameter of this argument
      val nameIndex = source.indexOf("--table") + 1
      if (nameIndex >= source.length) {
        throw new Exception("No table name was found")
      }

      // get and check name of this parameter
      val name = source(nameIndex)
      name match {
        case "bourke" => return new PaulBourkesConverter
        case "fun" => return new FunNonLinearConverter
        case _ => throw new Exception("Invalid table name")
      }
    }

    // custom linear table will be used using given characters
    else if(customTableArgCnt == 1) {
      // look one ahead for parameter of this argument
      val charsIndex = source.indexOf("--custom-table") + 1
      if (charsIndex >= source.length) {
        throw new Exception("No characters list for table were found")
      }
      val chars = source(charsIndex)
      if(chars.isEmpty) {
        throw new Exception("Empty list of characters for table")
      }
      return new CustomLinearConverter(chars)
    }

    // return default converter
    new PaulBourkesConverter
  }
}