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
    val loadedImage = ImageIO.read(file)
    val height = loadedImage.getHeight
    val width = loadedImage.getWidth
    val array = Array.ofDim[RGBPixel](height, width)
    val grid = RGBGrid(array)
    val image = RGBImage(grid)
    for (h <- 0 until height) {
      for (w <- 0 until width) {
        val color = new Color(loadedImage.getRGB(w, h))
        val redValue = color.getRed
        val greenValue = color.getGreen
        val blueValue = color.getBlue
        val pixel = RGBPixel(redValue, greenValue, blueValue)
        image.setItemOnPos(h, w, pixel)
      }
    }
    image
  }
}
