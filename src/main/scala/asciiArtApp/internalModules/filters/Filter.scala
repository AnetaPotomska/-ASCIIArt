package asciiArtApp.internalModules.filters

trait Filter[T] {
  def filter(item: T): Option[T]
}
