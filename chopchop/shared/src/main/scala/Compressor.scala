package chopchop

import scala.util.Try

// https://github.com/nodeca/pako
//https://github.com/cornerman/covenant/blob/e618443e27ba5b90ad9ee983a67c6e9e7a4ff1bd/http/js/src/main/scala/JsMessageBuilder.scala

trait Compressor {
  def compress(data:Array[Byte]):Array[Byte]
  def decompress(data:Array[Byte]):Try[Array[Byte]]
}
