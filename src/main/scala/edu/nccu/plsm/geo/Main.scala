package edu.nccu.plsm.geo

import java.util.concurrent.TimeUnit

import edu.nccu.plsm.geo.datum.TWD97

/**
 * @version
 * @since
 */
object Main extends App {
  def time = {
    val t0 = System.nanoTime()
    TWD97.transverseMercator("""121°01'23.123"""", "23d42m23.61s")
    TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - t0)
  }

  val test = (1 to 10000).map(_ => time.toDouble)
  val average = test.sum / test.length
  println(s"Average: ${average.toString} ms")
}
