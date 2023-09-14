package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.SinglePixelManipulation

class InvertFilter extends SinglePixelManipulation {
  private def determineFinalInvertedValue(greyscaleValue: Int): Int = {
    255 - greyscaleValue
  }

  override def pixelManipulator(value: Int): Int = {
    determineFinalInvertedValue(value)
  }
}
