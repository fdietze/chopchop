package chopchop

import scala.util.Try

object Zlib extends Compressor {
  // ported from https://dzone.com/articles/how-compress-and-uncompress

  def compress(data: Array[Byte]): Array[Byte] = {
  }

  def decompress(data: Array[Byte]): Try[Array[Byte]] = {
    Try{

    }
  }
}
