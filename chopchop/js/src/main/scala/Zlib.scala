package chopchop

import chopchop.facades.pako.pako

import scala.scalajs.js
import scala.scalajs.js.typedarray.byteArray2Int8Array
import scala.util.Try
import scala.scalajs.js.typedarray.Uint8Array
import scala.scalajs.js.typedarray.TypedArrayBuffer
import scala.scalajs.js.typedarray.Int8Array
import scala.scalajs.js.JSConverters._

object Zlib extends Compressor {
  // http://nodeca.github.io/pako/
  
  //TODO: less copying!
  private def uint8ArrayToArrayByte(data: Uint8Array):Array[Byte] = {
    new Int8Array(data).toArray
  }

  private def arrayByteToUint8Array(data: Array[Byte]):Uint8Array = {
    new Uint8Array(byteArray2Int8Array(data))
  }

  def compress(data: Array[Byte]): Array[Byte] = {
    val input = arrayByteToUint8Array(data)
    val output = pako.deflate(input)
    uint8ArrayToArrayByte(output)
  }

  def decompress(data: Array[Byte]): Try[Array[Byte]] = {
    Try{
    val input = arrayByteToUint8Array(data)
    val output = pako.inflate(input)
    uint8ArrayToArrayByte(output)
    }
  }
}
