package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness

import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.SinglePixelManipulation

class BrightnessFilter(brightnessValue: Int) extends SinglePixelManipulation() {
  private def determineFinalBrightnessValue(greyscaleValue: Int): Int = {
    val tmpCalc = greyscaleValue + brightnessValue
    if(tmpCalc < 0) {
      return 0
    }
    else if(tmpCalc > 255) {
      return 255
    }
    tmpCalc
  }

  override def pixelManipulator(value: Int): Int = {
    determineFinalBrightnessValue(value)
  }
}
