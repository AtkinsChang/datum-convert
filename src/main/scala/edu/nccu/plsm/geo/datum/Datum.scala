package edu.nccu.plsm.geo.datum

import edu.nccu.plsm.geo.common
import edu.nccu.plsm.geo.projection.TMParameters


/**
 * @version
 * @since
 */
abstract class Datum(_a: String, _invF: String) {
  lazy val inverseFlattening = BigDecimal(_invF)
  lazy val a = BigDecimal(_a)
  lazy val b = a * (inverseFlattening - 1) / inverseFlattening
  lazy val f = inverseFlattening.pow(-1)
  lazy val f2 = (inverseFlattening - 1).pow(-1)
  lazy val f3 = (inverseFlattening * 2 - 1).pow(-1)
  lazy val n = common.Math.lazyExponential(f3)
  lazy val eSquare = f * 2 - f * f
  lazy val e2Square = eSquare / (1 - eSquare)
}

abstract class GRS67 extends Datum("6378160", "298.247167427") {
}

abstract class GRS80 extends Datum("6378137", "298.257222100882711243") {
}

abstract class WGS84 extends Datum("6378137", "298.257223563") {
}

object TWD67 extends GRS67 {
  // TODO
}

object TWD97 extends GRS80 with TMParameters {
  override lazy val long0 = common.Math.toRadian("121")
  override lazy val k = BigDecimal(0.9999)
  override lazy val dx = BigDecimal(250000)
}