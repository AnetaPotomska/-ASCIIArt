package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

trait ImageFromFileLoader extends RGBImageLoader {
  def loadImageWithImageIO(file: File): RGBImage = {
    val image = ImageIO.read(file)
    val height = image.getHeight
    val width = image.getWidth
    val grid = Array.ofDim[RGBPixel](height, width)
    for (h <- 0 to height) {
      for (w <- 0 to width) {
        val color = new Color(image.getRGB(w, h))
        val redValue = color.getRed
        val greenValue = color.getGreen
        val blueValue = color.getBlue
        val pixel = RGBPixel(redValue, greenValue, blueValue)
        grid(h)(w) = pixel
      }
    }
    RGBImage(RGBGrid(grid))
  }
}
