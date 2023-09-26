package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.brightness

import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.SinglePixelManipulation

class BrightnessFilter(brightnessValue: Int) extends SinglePixelManipulation() {
  private def determineFinalBrightnessValue(greyscaleValue: Int): Int = {
    // calc
    val tmpCalc = greyscaleValue + brightnessValue

    // correct for out of bounds
    if(tmpCalc < 0) {
      return 0
    }
    else if(tmpCalc > 255) {
      return 255
    }

    // ret
    tmpCalc
  }

  override def pixelManipulator(value: Int): Int = {
    determineFinalBrightnessValue(value)
  }
}
