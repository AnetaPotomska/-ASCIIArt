package asciiArtApp.internalModules.exporters.asciiImage

import java.io.{File, FileOutputStream}

class FileOutputExporter(file: File) extends StreamAsciiImageExporter(new FileOutputStream(file)) {

}
