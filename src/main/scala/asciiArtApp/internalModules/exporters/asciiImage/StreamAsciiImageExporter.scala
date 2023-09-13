package asciiArtApp.internalModules.exporters.asciiImage

import asciiArtApp.models.images.AsciiImage

import java.io.OutputStream

class StreamAsciiImageExporter(outputStream: OutputStream) extends  AsciiImageExporter {
  private var closed = false

  protected def exportToStream(image: AsciiImage): Unit = {
    if (closed) {
      throw new Exception("The stream is closed")
    }
    val height = image.getHeight
    val width = image.getWidth
    var toWrite = ""
    for (h <- 0 to height) {
      var row = ""
      for (w <- 0 to width) {
        val asciiChar = image.getItemOnPos(h, w)
        row = row.appended(asciiChar.character)
      }
      toWrite += row.appended('\n')
    }
    outputStream.write(toWrite.getBytes("UTF-8"))
    outputStream.flush()
  }

  def close(): Unit = {
    if(closed) {
      return
    }
    outputStream.close()
    closed = true
  }

  override def `export`(item: AsciiImage): Unit = {
    exportToStream(item)
  }
}
