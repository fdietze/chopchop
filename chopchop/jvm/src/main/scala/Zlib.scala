package chopchop

import java.util.zip.Deflater
import java.util.zip.Inflater
import java.io.ByteArrayOutputStream
import scala.util.Try

object Zlib extends Compressor {
  // ported from https://dzone.com/articles/how-compress-and-uncompress

  def compress(data: Array[Byte]): Array[Byte] = {
    val deflater = new Deflater()
    deflater.setInput(data)
    val outputStream = new ByteArrayOutputStream(data.length)
    deflater.finish()

    val buffer = new Array[Byte](1024)
    while (!deflater.finished()) {
      val count = deflater.deflate(buffer) // returns the generated code... index
      outputStream.write(buffer, 0, count)
    }
    outputStream.close()
    outputStream.toByteArray()
  }

  def decompress(data: Array[Byte]): Either[Throwable, Array[Byte]] = {
    Try{
      val inflater = new Inflater()
      inflater.setInput(data)
      val outputStream = new ByteArrayOutputStream(data.length)
      val buffer = new Array[Byte](1024)
      while (!inflater.finished()) {
        val count = inflater.inflate(buffer)
        outputStream.write(buffer, 0, count)
      }
      outputStream.close()
      outputStream.toByteArray();
    }.toEither
  }
}
