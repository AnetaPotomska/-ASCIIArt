package externalModules.converters.intToCharByTable.nonLinear


class FunNonLinearConverter(chars: String = " .:-=+*#%@") extends NonLinearIntToCharConverter {
  override def convert(item: Int): Char = {
    if(item > 230) {
      chars(0)
    }
    else if (item > 204) {
      chars(1)
    }
    else if (item > 186) {
      chars(2)
    }
    else if (item > 169) {
      chars(3)
    }
    else if (item > 152) {
      chars(4)
    }
    else if (item > 130) {
      chars(5)
    }
    else if (item > 111) {
      chars(6)
    }
    else if (item > 56) {
      chars(7)
    }
    else if (item > 31) {
      chars(8)
    }
    chars(9)
  }
}
