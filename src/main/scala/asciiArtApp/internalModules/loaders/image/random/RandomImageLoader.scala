package asciiArtApp.internalModules.loaders.image.random

import asciiArtApp.internalModules.loaders.image.RGBImageLoader

trait RandomImageLoader extends RGBImageLoader {
  val maxImageHeight = 2000
  val minImageHeight = 20

  val maxImageWidth = 2000
  val minImageWidth = 20

  val maxPixelValue = 255
  val minPixelValue = 0
}
