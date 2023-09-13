package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.models.images.RGBImage

import java.io.File

class PngLoader(file: File) extends ImageFromFileLoader {
  override def load(): RGBImage = {
    loadImageWithImageIO(file)
  }
}
