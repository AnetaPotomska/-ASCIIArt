package asciiArtApp.console

import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.views.ConsoleView


object Main extends App {
  val controller = new ConsoleController()
  val view = new ConsoleView(controller);
  try {
    view.run(args)
  }
  catch {
    case e: Exception =>
      println("ERROR: " + e.getMessage)
  }
}