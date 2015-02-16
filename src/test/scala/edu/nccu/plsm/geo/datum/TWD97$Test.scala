package edu.nccu.plsm.geo.datum

import org.specs2.mutable.Specification

/**
 * @version
 * @since
 */
class TWD97$Test extends Specification {

  val delta = 0.001

  "Transverse mercator projection" should {
    "project (121.1234,23.5678)" in {
      val result = TWD97.transverseMercator(121.1234,23.5678)
      result._1.toDouble must beCloseTo (262596.493 +/- delta)
      result._2.toDouble must beCloseTo (2607164.976 +/- delta)
    }
    "project (121d10m30.301s, 23d42m10.3s)" in {
      val result = TWD97.transverseMercator("121d10m30.301s", "23d42m10.3s")
      result._1.toDouble must beCloseTo (267853.967 +/- delta)
      result._2.toDouble must beCloseTo (2622127.443 +/- delta)
    }
  }

}
