package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.images.{Image, RGBImage}

import java.io.File

class PngLoader(file: File) extends ImageFromFileLoader {
  override def load(): RGBImage = {
    loadImageWithImageIO(file)
  }
}
