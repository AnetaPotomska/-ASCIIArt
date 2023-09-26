package externalModules.exporters.text

import java.io.OutputStream

class StreamTextExporter(outputStream: OutputStream) extends TextExporter {
  private var closed = false

  protected def exportToStream(text: String): Unit = {
    if (closed) {
      throw new Exception("The stream is closed")
    }
    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
  }

  def close(): Unit = {
    if(closed) {
      return
    }
    outputStream.close()
    closed = true
  }

  override def `export`(item: String): Unit = {
    exportToStream(item)
  }
}
