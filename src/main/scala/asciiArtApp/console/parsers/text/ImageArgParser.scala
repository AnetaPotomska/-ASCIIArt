package asciiArtApp.console.parsers.text

import asciiArtApp.internalModules.loaders.image.RGBImageLoader
import asciiArtApp.internalModules.loaders.image.fromFile.{JpgLoader, PngLoader}
import asciiArtApp.internalModules.loaders.image.random.RandomImageGenerator

import java.io.File

case class ImageArgParser() extends TextParser[RGBImageLoader] {
  override def parse(source: Array[String]): RGBImageLoader = {
    val pathImageArgCnt = source.count(_.equals("--image"))
    val randomImageArgCnt = source.count(_.equals("--image-random"))

    if(pathImageArgCnt == 0 && randomImageArgCnt == 0) {
      throw new Exception("No image source was found")
    }
    else if(pathImageArgCnt + randomImageArgCnt > 1) {
      throw new Exception("Found too many image sources")
    }
    else if(pathImageArgCnt == 1) {
      val pathIndex = source.indexOf("--image") + 1
      if(pathIndex >= source.length) {
        throw new Exception("No image path was found")
      }
      val path = source(pathIndex)
      val lastDotIndex = path.lastIndexOf('.')
      if(lastDotIndex + 1 >= path.length) {
        throw new Exception("No extension found in path to image")
      }
      val format = path.substring(lastDotIndex + 1)
      val file = new File(path)
      if(!file.exists()) {
        throw new Exception("Couldn't load file with image")
      }
      format match {
        case "jpg" => return new JpgLoader(file)
        case "png" => return new PngLoader(file)
        case _ => throw new Exception("Couldn't load file with image")
      }
    }
    new RandomImageGenerator
  }
}
