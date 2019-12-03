package chopchop.facades.pako

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.typedarray.Uint8Array

// http://nodeca.github.io/pako/#deflate

@js.native
@JSImport("pako", JSImport.Default)
object pako extends js.Object {

  def deflate(data: Uint8Array): Uint8Array = js.native
  def inflate(data: Uint8Array): Uint8Array = js.native
}
