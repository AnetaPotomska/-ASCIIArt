package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.images.{Image, RGBImage}

import javax.imageio.ImageIO
import java.io.File

class JpgLoader(file: File) extends ImageFromFileLoader {
  override def load(): RGBImage = {
    loadImageWithImageIO(file)
  }
}
