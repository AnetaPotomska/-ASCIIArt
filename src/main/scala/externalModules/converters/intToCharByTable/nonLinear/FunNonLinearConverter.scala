package externalModules.converters.intToCharByTable.nonLinear

// SOURCE: https://paulbourke.net/dataformats/asciiart/
class FunNonLinearConverter(chars: String = " .:-=+*#%@") extends NonLinearIntToCharConverter {
  override def convert(item: Int): Option[Char] = {
    // check
    if(chars.length < 10) {
      return None
    }

    // ugly distribution
    if(item > 230) {
      return Some(chars(0))
    }
    else if (item > 204) {
      return Some(chars(1))
    }
    else if (item > 186) {
      return Some(chars(2))
    }
    else if (item > 169) {
      return Some(chars(3))
    }
    else if (item > 152) {
      return Some(chars(4))
    }
    else if (item > 130) {
      return Some(chars(5))
    }
    else if (item > 111) {
      return Some(chars(6))
    }
    else if (item > 56) {
      return Some(chars(7))
    }
    else if (item > 31) {
      return Some(chars(8))
    }
    Some(chars(9))
  }
}
