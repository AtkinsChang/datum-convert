package edu.nccu.plsm.geo.projection

/**
 * @version
 * @since
 */
object TransverseMercator {

  def project(long: BigDecimal, lat: BigDecimal, datum: TMParameters): (BigDecimal, BigDecimal) = {
    import edu.nccu.plsm.geo.common.Math._

    val p = lazyExponential(long - datum.long0)

    // TODO big math calculation instead double
    val sinLat = lazyExponential(BigDecimal(math.sin(lat.doubleValue())))
    val cosLat = lazyExponential(BigDecimal(math.cos(lat.doubleValue())))
    val tanLat_2 = BigDecimal(math.tan(lat.doubleValue())).pow(2)

    val n = datum.n
    val nu = datum.a / sqrt(1 - datum.eSquare * sinLat(2))

    val _a = (1 - n(1)
      + ((n(2) - n(3)) * 5 / 4)
      + ((n(4) - n(5)) * 81 / 64))
    val _b = (3 * n(1) / 2) *
      (1 - n(1)
        + ((n(2) - n(3)) * 7 / 8)
        + ((n(4) - n(5)) * 55 / 64))
    val _c = (15 * n(2) / 16) *
      (1 - n(1)
        + ((n(2) - n(3)) * 3 / 4))
    val _d = (35 * n(3) / 48) *
      (1 - n(1)
        + ((n(2) - n(3)) * 11 / 16))
    val _e = (315 * n(4) / 51) *
      (1 - n(1))

    val _s = datum.a * (
      _a * lat
        - _b * math.sin((lat * 2).doubleValue())
        + _c * math.sin((lat * 4).doubleValue())
        - _d * math.sin((lat * 6).doubleValue())
        + _e * math.sin((lat * 8).doubleValue())
      )

    val k1 = _s
    val k2 = nu * sinLat(1) * cosLat(1) / 2
    val k3 = nu * sinLat(1) * cosLat(3) / 24 * (
      5
        - tanLat_2
        + 9 * datum.e2Square * cosLat(2)
        + 4 * datum.e2Square.pow(2) * cosLat(4)
      )
    val k4 = nu * cosLat(1)
    val k5 = nu *
      cosLat(3) / 6 * (
      1
        - tanLat_2
        + datum.e2Square * cosLat(2)
      )

    val northing = datum.k * (k1 + k2 * p(2) + k3 * p(4))
    val easting = datum.k * (k4 * p(1) + k5 * p(3)) + datum.dx
    (easting, northing)
  }

  // TODO
  def reverse(x: BigDecimal, y: BigDecimal, datum: TMParameters): (BigDecimal, BigDecimal) = ???

}
