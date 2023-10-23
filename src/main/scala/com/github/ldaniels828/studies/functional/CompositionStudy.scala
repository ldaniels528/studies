package com.github.ldaniels828.studies.functional

import com.github.ldaniels828.studies.AutoClose
import org.slf4j.LoggerFactory

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

/**
 * Simple Composition Demonstration
 */
object CompositionStudy extends App {
  private val logger = LoggerFactory.getLogger(getClass)

  ////////////////////////////////////////////////////
  //  FUNCTIONS AVAILABLE FOR COMPOSITION
  ////////////////////////////////////////////////////

  private val bytesToString: Any => String = {
    case b: Array[Byte] => new String(b)
  }

  private val compress: Any => Array[Byte] = {
    case bytes: Array[Byte] =>
      new ByteArrayOutputStream(bytes.length) use { baos =>
        new GZIPOutputStream(baos) use { gzos =>
          gzos.write(bytes)
          gzos.finish()
          baos.toByteArray
        }
      }
  }

  private val decompress: Any => Array[Byte] = {
    case bytes: Array[Byte] =>
      new ByteArrayInputStream(bytes) use { bain =>
        new GZIPInputStream(bain) use { gzos =>
          val count = gzos.read(bytes)
          bytes.slice(0, count)
        }
      }
  }

  private val reverse: Any => String = {
    case s: String => s.reverse
  }

  private val stringToBytes: Any => Array[Byte] = {
    case s: String => s.getBytes
  }

  ////////////////////////////////////////////////////
  //  COMPOSITION OPERATION
  ////////////////////////////////////////////////////

  private val composition = List(reverse, stringToBytes, compress, decompress, bytesToString)
  private val result = composition.foldLeft[Any]("HelloWorld") { case (x, f) => f(x) }
  logger.info(s"result: $result")

}