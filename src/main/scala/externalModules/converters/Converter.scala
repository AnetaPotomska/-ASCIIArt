package externalModules.converters

trait Converter[T, S] {
  def convert(item: T) : S
}
