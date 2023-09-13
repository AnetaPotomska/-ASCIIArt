package asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.invert

import asciiArtApp.internalModules.filters.image.ImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.GreyscaleImageFilter
import asciiArtApp.internalModules.filters.image.greyscale.singlePixelManipulation.SinglePixelManipulation
import asciiArtApp.models.grids.GreyscaleGrid
import asciiArtApp.models.images.{GreyscaleImage, Image}
import asciiArtApp.models.pixels.{GreyscalePixel, Pixel}

class InvertFilter extends SinglePixelManipulation {
  private def determineFinalInvertedValue(greyscaleValue: Int): Int = {
    255 - greyscaleValue
  }

  override def pixelManipulator(value: Int): Int = {
    determineFinalInvertedValue(value)
  }
}
