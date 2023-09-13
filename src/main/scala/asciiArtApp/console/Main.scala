package asciiArtApp.console

import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.parsers.text.TextParser
import asciiArtApp.console.views.ConsoleView

object Main extends App {
  val controller = new ConsoleController()
  val view = new ConsoleView(controller);
  view.run(args.toArray);
}