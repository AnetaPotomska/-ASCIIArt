package asciiArtApp.console.parsers

trait Parser[T, S] {
  def parse(source: T): S
}
