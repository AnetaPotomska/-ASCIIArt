package asciiArtApp.internalModules.filters.image

import asciiArtApp.internalModules.filters.Filter
import asciiArtApp.models.images.Image
import asciiArtApp.models.pixels.Pixel

trait ImageFilter[T <: Image[_]] extends Filter[T] {

}
