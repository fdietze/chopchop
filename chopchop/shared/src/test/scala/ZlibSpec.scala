package chopchop.test

import org.scalatest.matchers._
import org.scalatest.freespec._

class ZlibSpec extends AnyFreeSpec with must.Matchers {

  "compress" - {
    val data = Array[Byte](3,4,4,4,3,4,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4)
    val result = chopchop.Zlib.compress(data)
    result.toList mustEqual Array[Byte](120, -100, 99, 102, 97, 97, 97, 6, 66, 84, 0, 0, 3, -46, 0, 86).toList
  }

  // "decompress" - {
  //   val buffer = new mutable.ArrayBuffer[Int]
  //   loop(3, start = 1)(buffer += _)
  //   buffer.toList mustEqual List(1, 2)
  //   ()
  // }
}
