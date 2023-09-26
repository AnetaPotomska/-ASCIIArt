package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.internalModules.loaders.image.fromFile.{JpgLoader, PngLoader}
import asciiArtApp.internalModules.loaders.image.random.RandomImageGenerator

import java.io.File
import scala.util.Random

class ImageArgParser() extends TextParser[RGBImageLoader] {
  override def parse(source: Array[String]): RGBImageLoader = {
    // get count of image related args
    val pathImageArgCnt = source.count(_.equals("--image"))
    val randomImageArgCnt = source.count(_.equals("--image-random"))

    // there is support only for exactly 1 image related argument
    if(pathImageArgCnt == 0 && randomImageArgCnt == 0) {
      throw new Exception("No image source was found")
    }
    else if(pathImageArgCnt + randomImageArgCnt > 1) {
      throw new Exception("Found too many image sources")
    }

    // image from path will be used
    else if(pathImageArgCnt == 1) {
      // look one ahead for parameter of this argument
      val pathIndex = source.indexOf("--image") + 1
      if(pathIndex >= source.length) {
        throw new Exception("No image path was found")
      }
      val path = source(pathIndex)
      // get extension of this parameter (/path)
      val lastDotIndex = path.lastIndexOf('.')
      if(lastDotIndex == -1 || lastDotIndex + 1 >= path.length) {
        throw new Exception("No extension found in path to image")
      }
      val format = path.substring(lastDotIndex + 1)

      // open file
      val file = new File(path)

      // check file for existence, if-is-directory, and readability
      if(!file.exists()) {
        throw new Exception("Couldn't load file with image, file doesn't exist")
      }
      else if (file.isDirectory) {
        throw new Exception("Couldn't load file with image, file is a directory");
      }
      else if (!file.canRead) {
        throw new Exception("Couldn't load file with image, file is not readable");
      }

      // get and check file extension
      format match {
        case "jpg" => return new JpgLoader(file)
        case "png" => return new PngLoader(file)
        case _ => throw new Exception("Not supported extension for image")
      }
    }

    // random image will be generated
    new RandomImageGenerator(Random, 20,200, 20, 200)
  }
}
