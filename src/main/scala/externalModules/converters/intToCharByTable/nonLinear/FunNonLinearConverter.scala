package externalModules.converters.intToCharByTable.nonLinear

// SOURCE: https://paulbourke.net/dataformats/asciiart/
class FunNonLinearConverter(chars: String = " .:-=+*#%@") extends NonLinearIntToCharConverter {
  override def convert(item: Int): Char = {
    if(item > 230) {
      return chars(0)
    }
    else if (item > 204) {
      return chars(1)
    }
    else if (item > 186) {
      return chars(2)
    }
    else if (item > 169) {
      return chars(3)
    }
    else if (item > 152) {
      return chars(4)
    }
    else if (item > 130) {
      return chars(5)
    }
    else if (item > 111) {
      return chars(6)
    }
    else if (item > 56) {
      return chars(7)
    }
    else if (item > 31) {
      return chars(8)
    }
    chars(9)
  }
}
