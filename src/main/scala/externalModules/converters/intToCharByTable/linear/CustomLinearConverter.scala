package externalModules.converters.intToCharByTable.linear

/**
 *
 * @param chars are expected to be darkest to lightest for normal effect (not inverted)
 */
class CustomLinearConverter(chars: String) extends LinearIntToCharConverter {
  override def convert(item: Int): Char = {
    // size of range for one character
    val range = 255.0 / chars.length

    // calc
    var calc = item / range

    // correction
    if (calc.ceil >= chars.length)
      calc = chars.length - 1

    chars(calc.toInt)
  }
}
