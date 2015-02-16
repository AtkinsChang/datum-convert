package edu.nccu.plsm.geo.common

import edu.nccu.plsm.geo.datum.TWD97
import org.specs2.mutable.Specification

/**
 * @version
 * @since
 */
class Math$Test extends Specification {

  val delta = 0.00001

  "Convert to radian" should {
    "accept d m s format" in {
      Math.toRadian("121d30m0s").doubleValue() must beCloseTo  ((121.5 * math.Pi / 180) +/- delta)
    }
    "accept ° ' \" format'" in {
      Math.toRadian("121d30m0s").doubleValue() must beCloseTo  ((121.5 * math.Pi / 180) +/- delta)
    }
    "accept decimal format" in {
      Math.toRadian("121d30m0s").doubleValue() must beCloseTo  ((121.5 * math.Pi / 180) +/- delta)
    }
    "accept numeric format" in {
      Math.toRadian(BigDecimal(121.5)).doubleValue() must beCloseTo  ((121.5 * math.Pi / 180) +/- delta)
    }
  }
}
