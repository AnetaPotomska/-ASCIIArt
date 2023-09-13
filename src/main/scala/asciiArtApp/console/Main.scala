package asciiArtApp.console

import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.parsers.text.TextParser
import asciiArtApp.console.views.ConsoleView

import scala.util.Failure

object Main extends App {
  val controller = new ConsoleController()
  val view = new ConsoleView(controller);
  try {
    view.run(args.toArray)
  }
  catch {
    case e: Exception =>
      println("ERROR: " + e.getMessage)
  }
}