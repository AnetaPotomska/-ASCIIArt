package externalModules.converters.stringToInt

class StringNumberToIntConverter extends StringToIntConverter {
  override def convert(item: String): Int = {
    var stringNumber = item
    var isNegative = false
    if (stringNumber(0) == '-') {
      isNegative = true
      stringNumber = stringNumber.substring(1)
    }
    // check if all chars are digits
    if (!(stringNumber forall Character.isDigit)) {
      throw new Exception("Given value isn't number")
    }
    var intValue = stringNumber.toInt
    if (isNegative) {
      intValue = -1 * intValue
    }
    intValue
  }
}
