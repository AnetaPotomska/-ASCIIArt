package externalModules.converters.intToCharByTable.linear

/**
 *
 * @param chars are expected to be darkest to lightest for normal effect (not inverted)
 */
class CustomLinearConverter(chars: String) extends LinearIntToCharConverter {
  override def convert(item: Int): Option[Char] = {
    val len = chars.length
    if(len > 255 || len < 0) {
      return None
    }
    // size of range for one character
    val range = 255.0 / len

    // calc
    var calc = item / range

    // correction
    if (calc.ceil >= len)
      calc = len - 1

    Some(chars(calc.toInt))
  }
}
