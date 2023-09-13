package externalModules.converters.intToCharByTable.linear

class CustomLinearConverter(chars: String) extends LinearIntToCharConverter {
  override def convert(item: Int): Char = {
    val range = 255.0 / chars.length
    var toRet = item / range
    if (toRet >= chars.length)
      toRet = chars.length - 1
    chars(toRet.toInt)
  }
}
