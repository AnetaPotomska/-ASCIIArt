package asciiArtApp.internalModules.loaders

trait Loader[S] {
  def load() : Option[S]
}
