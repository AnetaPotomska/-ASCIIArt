package externalModules.converters.stringToInt

class StringNumberToIntConverter extends StringToIntConverter {
  override def convert(item: String): Option[Int] = {
    if(item.isEmpty) {
      return None
    }

    var stringNumber = item
    var isNegative = false

    // correct if num starts with -
    if (stringNumber(0) == '-') {
      isNegative = true
      stringNumber = stringNumber.substring(1)
    }

    // correct if positive num starts with +
    else if (stringNumber(0) == '+') {
      stringNumber = stringNumber.substring(1)
    }

    // check if all chars are digits
    if (stringNumber.isEmpty || !(stringNumber forall Character.isDigit)) {
      return None
    }

    // convert it to final int
    var intValue = stringNumber.toInt
    if (isNegative) {
      intValue = -1 * intValue // correct it if it is negative
    }
    Some(intValue)
  }
}
