package chopchop.test

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ZlibSpec extends AnyFlatSpec with Matchers {

  "zlib" should "compress empty" in {
    val data = Array[Byte]()
    val result = chopchop.Zlib.compress(data)
    result should contain theSameElementsAs Array[Byte](120, -100, 3, 0, 0, 0, 0, 1)
    chopchop.Zlib.decompress(result).get should contain theSameElementsAs data
  }

  "zlib" should "compress single char" in {
    val data = Array[Byte](20)
    val result = chopchop.Zlib.compress(data)
    result should contain theSameElementsAs Array[Byte](120, -100, 19, 1, 0, 0, 21, 0, 21)
    chopchop.Zlib.decompress(result).get should contain theSameElementsAs data
  }

  "zlib" should "compress repeating sequence" in {
    val data = Array[Byte](4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4)
    val result = chopchop.Zlib.compress(data)
    result should contain theSameElementsAs Array[Byte](120, -100, 99, 97, -63, 6, 0, 4, 10, 0, 89)
    chopchop.Zlib.decompress(result).get should contain theSameElementsAs data
  }

  "zlib" should "compress random sequence, length:1050" in {
    val data = Array[Byte](-56, -94, -25, -94, 47, 27, 86, 56, 17, 44, 67, 3, -115, -117, -83, -22, -119, 88, -29, -39, 48, 25, -13, -61, 19, 16, -46, 107, 9, 31, 46, 100, -81, 8, 102, -36, 87, 42, -58, 90, 16, 24, 3, -40, 86, 24, -40, -87, 108, -121, -9, 70, -27, -11, 35, -60, 88, -76, 53, 49, 72, 40, -106, -100, -86, 122, 75, -83, 124, -34, -68, -97, -76, -106, 107, 94, -79, -100, 92, 97, 95, 52, 105, 56, -27, 111, 44, -88, -33, -122, -88, -96, -66, 13, -57, 65, 37, 94, -58, -125, 13, 101, -21, 81, 53, -118, 22, -75, -96, -3, 24, 99, 9, 74, 40, 123, 80, -118, 77, 14, -86, 82, 24, 3, 110, 78, -74, -44, -94, -4, 8, 36, -7, -1, 4, 117, -3, 40, -97, 0, 73, -78, 18, 121, 101, 68, 122, -125, 9, -95, 56, -122, 44, -58, -72, 42, -64, 121, 101, 90, 56, 114, 82, 54, 55, -110, -45, 35, 42, -48, -100, 118, 118, 100, 97, -7, 68, -121, 19, -86, 56, 61, -112, 22, -49, -19, -56, -17, 2, 88, 6, -42, 14, -20, -74, 26, 64, 60, -95, 66, 15, -90, 53, 125, 22, 33, -99, -61, -55, 4, -107, -55, -57, -7, -2, -84, 110, 126, -99, 33, -30, -94, 75, -41, -27, 54, 65, 29, -112, 8, -119, 53, -77, 12, 115, -52, -70, 24, -78, -34, -21, 51, -88, 38, -26, 119, -106, 79, -37, -17, -71, 65, -11, 77, -24, 31, -116, -99, 71, 30, 40, -88, -128, -22, 80, -28, -63, 57, 24, -16, 59, -88, -10, 86, 51, -38, -120, 99, 104, 74, 90, 103, 2, -114, 52, 95, 127, 1, 105, 23, 14, -92, 15, 63, 45, -17, -43, -99, 72, -71, -116, -96, -54, -56, 10, 18, 96, -86, -29, -121, -117, 64, 36, 114, 80, -74, 122, 87, -122, 59, -9, -58, 15, 20, -52, 64, -43, 122, 101, 29, -3, 43, -110, -24, -78, 106, -102, 81, 110, 92, -12, -13, -2, -5, -26, -113, -12, 24, 7, 43, -36, -50, 66, -56, -119, -125, -18, 92, 1, 77, -42, 108, -92, -60, 77, 76, 76, -125, -17, -128, -115, 14, -7, 46, 29, 20, 63, -115, -18, -120, 0, -54, 22, 115, 109, 79, 33, 40, -124, 123, -98, 1, 51, 62, 38, 41, 4, -90, -66, 88, -93, 16, 54, 22, -11, 124, -111, 6, 69, -82, -26, 111, -20, 56, -55, 23, 44, 23, -65, -14, -91, -55, -78, 3, 59, 62, -34, -8, -97, 70, -63, -48, 38, 77, -67, 52, 47, 51, 1, 43, 1, -87, 49, -107, 56, 57, 68, 34, 74, 31, -6, 71, -22, -43, -9, -105, -33, 54, -43, 11, 46, -90, -19, 126, 61, -123, 29, -79, -77, 28, -37, -3, -18, 111, 105, -14, -76, -127, -109, -26, 37, -69, -85, 26, -17, -68, -21, -112, -57, 1, 12, -110, 98, -92, -121, -37, 126, -128, 44, 11, -49, -18, -2, -102, 126, -52, 100, -59, 21, -121, -71, 48, 45, -16, -122, -26, -42, 101, 43, 30, -50, 74, 66, 53, 18, 89, -35, -123, -44, 69, -70, -119, -104, -51, 46, -27, -107, -55, -105, 126, 113, -44, 107, -111, 104, 36, -51, 41, -61, -50, 39, -47, -126, 34, -29, -126, -72, 116, -96, -9, 1, 50, 82, -38, 99, -32, 124, -61, 12, -18, 38, 66, -58, -13, -47, -121, -15, 28, 122, 6, 97, 105, -120, 97, -91, 96, 1, 47, -14, 48, -37, -104, 23, 94, -104, 86, 76, -10, 38, 18, 47, 71, 55, 113, 115, -26, 8, -112, 116, 76, -127, -92, 91, -121, -52, -13, -81, -42, -115, 52, 1, -47, 98, -74, -2, 85, 125, 72, -78, 58, -110, 96, 63, 67, 69, 24, -68, 45, 109, -108, -76, 113, -10, -57, -28, -42, -3, -92, 73, 82, 49, 114, 77, -78, -23, 118, -14, 30, -62, -77, -71, -7, 88, 90, -15, 91, -116, 46, 74, 99, -19, 61, 6, -122, 59, -49, 48, -17, -123, -26, -45, 111, 116, 38, -34, -20, 18, -115, 2, -114, -122, -64, -58, 116, -100, -59, -18, -112, -55, 73, -87, 97, -25, 37, -45, -5, -115, -10, -8, -20, 120, -59, -33, 60, 31, -103, -27, -125, 41, 15, -110, -18, -37, -33, 7, -57, 12, 85, 81, -78, -30, -56, -55, -4, 81, -6, 26, 77, -115, 79, -6, 30, -25, -112, -77, -86, -6, 82, 49, 38, 93, -113, -38, -8, -107, 40, -23, -39, 88, 40, 44, 123, 60, 127, 126, 74, 53, 59, -21, -39, -108, 84, 119, -62, 7, 115, -53, -48, 93, -128, -69, 126, -90, 73, 100, 24, -44, 55, -96, 105, -8, -71, 3, 124, -55, -103, 0, 123, -77, 86, -19, 22, 3, 97, -76, -49, 78, 121, 86, -1, 48, 126, 27, 21, -75, -2, -110, 2, -3, 21, -94, 25, -126, 37, 79, -43, 27, -50, 108, 65, 36, -113, -39, 11, -56, -96, 121, -42, -65, 55, -76, -95, 80, 28, 15, -66, -120, -35, 21, -99, 38, 62, 44, -103, -1, 37, -4, -63, 3, -29, 64, -52, 4, 69, -4, 33, -43, 58, -107, -22, 15, -84, -2, -16, 121, -45, -5, -101, -104, -81, 119, 52, 36, -21, -68, -41, -15, -25, -21, -15, -124, 73, -29, 77, -66, -12, -71, 66, 72, -109, 88, -96, 104, 69, 126, 118, 43, 8, 100, -81, -87, 85, -109, 2, -75, 58, -107, 73, -9, -50, 113, -76, 44, -52, 27, 21, -123, 118, 5, 70, 108, -125, 23, 25, -34, -3, 50, 94, 35, 119, 116, -9, -74, -123, 23, 5, -115, -116, 62, 60, 70, -101, -42, -14, 18, 82, -108, 53, -46, -44, -23, -80, -24, 78, -85, 31, -22, 66, 78, -103, 101, 19, 122, 34, -115, -92, 9, 36, 50, -64, 3, 59, 109, -17, -41, -6, 15, 82, 96, -64, 57, 92, 4, -60, -70, -18, -14, 84, 126, 2, -73, -48, -13, -16, 102, -50, -5, -121, 63, -88, -25, -103, 114, -125, 11, 19, 37, 25, -15, -39, 114, -116, -97, 93, 7, -38, -47, 3, 77, 108, 127, -110, -120, 39, -95, 82, 81, 89, -66, -24, -27, 1, 34, -92, -32, -119, 115, -13, -28, 3)
    val result = chopchop.Zlib.compress(data)
    result should contain theSameElementsAs Array[Byte](120, -100, 1, 26, 4, -27, -5, -56, -94, -25, -94, 47, 27, 86, 56, 17, 44, 67, 3, -115, -117, -83, -22, -119, 88, -29, -39, 48, 25, -13, -61, 19, 16, -46, 107, 9, 31, 46, 100, -81, 8, 102, -36, 87, 42, -58, 90, 16, 24, 3, -40, 86, 24, -40, -87, 108, -121, -9, 70, -27, -11, 35, -60, 88, -76, 53, 49, 72, 40, -106, -100, -86, 122, 75, -83, 124, -34, -68, -97, -76, -106, 107, 94, -79, -100, 92, 97, 95, 52, 105, 56, -27, 111, 44, -88, -33, -122, -88, -96, -66, 13, -57, 65, 37, 94, -58, -125, 13, 101, -21, 81, 53, -118, 22, -75, -96, -3, 24, 99, 9, 74, 40, 123, 80, -118, 77, 14, -86, 82, 24, 3, 110, 78, -74, -44, -94, -4, 8, 36, -7, -1, 4, 117, -3, 40, -97, 0, 73, -78, 18, 121, 101, 68, 122, -125, 9, -95, 56, -122, 44, -58, -72, 42, -64, 121, 101, 90, 56, 114, 82, 54, 55, -110, -45, 35, 42, -48, -100, 118, 118, 100, 97, -7, 68, -121, 19, -86, 56, 61, -112, 22, -49, -19, -56, -17, 2, 88, 6, -42, 14, -20, -74, 26, 64, 60, -95, 66, 15, -90, 53, 125, 22, 33, -99, -61, -55, 4, -107, -55, -57, -7, -2, -84, 110, 126, -99, 33, -30, -94, 75, -41, -27, 54, 65, 29, -112, 8, -119, 53, -77, 12, 115, -52, -70, 24, -78, -34, -21, 51, -88, 38, -26, 119, -106, 79, -37, -17, -71, 65, -11, 77, -24, 31, -116, -99, 71, 30, 40, -88, -128, -22, 80, -28, -63, 57, 24, -16, 59, -88, -10, 86, 51, -38, -120, 99, 104, 74, 90, 103, 2, -114, 52, 95, 127, 1, 105, 23, 14, -92, 15, 63, 45, -17, -43, -99, 72, -71, -116, -96, -54, -56, 10, 18, 96, -86, -29, -121, -117, 64, 36, 114, 80, -74, 122, 87, -122, 59, -9, -58, 15, 20, -52, 64, -43, 122, 101, 29, -3, 43, -110, -24, -78, 106, -102, 81, 110, 92, -12, -13, -2, -5, -26, -113, -12, 24, 7, 43, -36, -50, 66, -56, -119, -125, -18, 92, 1, 77, -42, 108, -92, -60, 77, 76, 76, -125, -17, -128, -115, 14, -7, 46, 29, 20, 63, -115, -18, -120, 0, -54, 22, 115, 109, 79, 33, 40, -124, 123, -98, 1, 51, 62, 38, 41, 4, -90, -66, 88, -93, 16, 54, 22, -11, 124, -111, 6, 69, -82, -26, 111, -20, 56, -55, 23, 44, 23, -65, -14, -91, -55, -78, 3, 59, 62, -34, -8, -97, 70, -63, -48, 38, 77, -67, 52, 47, 51, 1, 43, 1, -87, 49, -107, 56, 57, 68, 34, 74, 31, -6, 71, -22, -43, -9, -105, -33, 54, -43, 11, 46, -90, -19, 126, 61, -123, 29, -79, -77, 28, -37, -3, -18, 111, 105, -14, -76, -127, -109, -26, 37, -69, -85, 26, -17, -68, -21, -112, -57, 1, 12, -110, 98, -92, -121, -37, 126, -128, 44, 11, -49, -18, -2, -102, 126, -52, 100, -59, 21, -121, -71, 48, 45, -16, -122, -26, -42, 101, 43, 30, -50, 74, 66, 53, 18, 89, -35, -123, -44, 69, -70, -119, -104, -51, 46, -27, -107, -55, -105, 126, 113, -44, 107, -111, 104, 36, -51, 41, -61, -50, 39, -47, -126, 34, -29, -126, -72, 116, -96, -9, 1, 50, 82, -38, 99, -32, 124, -61, 12, -18, 38, 66, -58, -13, -47, -121, -15, 28, 122, 6, 97, 105, -120, 97, -91, 96, 1, 47, -14, 48, -37, -104, 23, 94, -104, 86, 76, -10, 38, 18, 47, 71, 55, 113, 115, -26, 8, -112, 116, 76, -127, -92, 91, -121, -52, -13, -81, -42, -115, 52, 1, -47, 98, -74, -2, 85, 125, 72, -78, 58, -110, 96, 63, 67, 69, 24, -68, 45, 109, -108, -76, 113, -10, -57, -28, -42, -3, -92, 73, 82, 49, 114, 77, -78, -23, 118, -14, 30, -62, -77, -71, -7, 88, 90, -15, 91, -116, 46, 74, 99, -19, 61, 6, -122, 59, -49, 48, -17, -123, -26, -45, 111, 116, 38, -34, -20, 18, -115, 2, -114, -122, -64, -58, 116, -100, -59, -18, -112, -55, 73, -87, 97, -25, 37, -45, -5, -115, -10, -8, -20, 120, -59, -33, 60, 31, -103, -27, -125, 41, 15, -110, -18, -37, -33, 7, -57, 12, 85, 81, -78, -30, -56, -55, -4, 81, -6, 26, 77, -115, 79, -6, 30, -25, -112, -77, -86, -6, 82, 49, 38, 93, -113, -38, -8, -107, 40, -23, -39, 88, 40, 44, 123, 60, 127, 126, 74, 53, 59, -21, -39, -108, 84, 119, -62, 7, 115, -53, -48, 93, -128, -69, 126, -90, 73, 100, 24, -44, 55, -96, 105, -8, -71, 3, 124, -55, -103, 0, 123, -77, 86, -19, 22, 3, 97, -76, -49, 78, 121, 86, -1, 48, 126, 27, 21, -75, -2, -110, 2, -3, 21, -94, 25, -126, 37, 79, -43, 27, -50, 108, 65, 36, -113, -39, 11, -56, -96, 121, -42, -65, 55, -76, -95, 80, 28, 15, -66, -120, -35, 21, -99, 38, 62, 44, -103, -1, 37, -4, -63, 3, -29, 64, -52, 4, 69, -4, 33, -43, 58, -107, -22, 15, -84, -2, -16, 121, -45, -5, -101, -104, -81, 119, 52, 36, -21, -68, -41, -15, -25, -21, -15, -124, 73, -29, 77, -66, -12, -71, 66, 72, -109, 88, -96, 104, 69, 126, 118, 43, 8, 100, -81, -87, 85, -109, 2, -75, 58, -107, 73, -9, -50, 113, -76, 44, -52, 27, 21, -123, 118, 5, 70, 108, -125, 23, 25, -34, -3, 50, 94, 35, 119, 116, -9, -74, -123, 23, 5, -115, -116, 62, 60, 70, -101, -42, -14, 18, 82, -108, 53, -46, -44, -23, -80, -24, 78, -85, 31, -22, 66, 78, -103, 101, 19, 122, 34, -115, -92, 9, 36, 50, -64, 3, 59, 109, -17, -41, -6, 15, 82, 96, -64, 57, 92, 4, -60, -70, -18, -14, 84, 126, 2, -73, -48, -13, -16, 102, -50, -5, -121, 63, -88, -25, -103, 114, -125, 11, 19, 37, 25, -15, -39, 114, -116, -97, 93, 7, -38, -47, 3, 77, 108, 127, -110, -120, 39, -95, 82, 81, 89, -66, -24, -27, 1, 34, -92, -32, -119, 115, -13, -28, 3, 108, 121, 5, 13)
    chopchop.Zlib.decompress(result).get should contain theSameElementsAs data
  }
}
