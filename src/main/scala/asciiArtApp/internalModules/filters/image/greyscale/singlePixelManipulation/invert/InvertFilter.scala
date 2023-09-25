package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.SinglePixelManipulation

class InvertFilter extends SinglePixelManipulation {
  private def determineFinalInvertedValue(greyscaleValue: Int): Int = {
    if (greyscaleValue < 0) {
      return 255
    }
    else if (greyscaleValue > 255) {
      return 0
    }
    255 - greyscaleValue
  }

  override def pixelManipulator(value: Int): Int = {
    determineFinalInvertedValue(value)
  }
}
