package externalModules.converters.intToCharByTable.linear

class CustomLinearConverter(chars: String) extends LinearIntToCharConverter {
  override def convert(item: Int): Char = {
    val range = 255.0 / chars.length
    var calc = item / range
    if (calc >= chars.length)
      calc = chars.length - 1
    chars(calc.toInt)
  }
}
