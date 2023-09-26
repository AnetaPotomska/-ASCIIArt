package asciiArtApp.internalModules.loaders.image.fromFile

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.models.grids.RGBGrid
import asciiArtApp.models.images.RGBImage
import asciiArtApp.models.pixels.RGBPixel

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

trait ImageFromFileLoader extends RGBImageLoader {
  def loadImageWithImageIO(file: File): Option[RGBImage] = {
    // load image with ImageIO
    val loadedImage = ImageIO.read(file)

    // init
    val height = loadedImage.getHeight
    val width = loadedImage.getWidth
    if(height <= 0 || width <= 0) {
      return None
    }
    val array = Array.ofDim[RGBPixel](height, width)
    val grid = RGBGrid(array)
    val image = RGBImage(grid)

    // go through every pixel
    for ((h, w) <- image) {
      // get rgb values
      val color = new Color(loadedImage.getRGB(w, h))
      val redValue = color.getRed
      val greenValue = color.getGreen
      val blueValue = color.getBlue

      // make rgb pixel out of it and save it to current position
      val pixel = RGBPixel(redValue, greenValue, blueValue)
      image.setItemOnPos(h, w, pixel)
    }
    Some(image)
  }
}
