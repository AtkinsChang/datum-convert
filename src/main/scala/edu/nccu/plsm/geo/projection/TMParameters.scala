package edu.nccu.plsm.geo.projection

import edu.nccu.plsm.geo.common

/**
 * @version
 * @since
 */
trait TMParameters {

  val a: BigDecimal
  val n: Stream[BigDecimal]
  val eSquare: BigDecimal
  val e2Square: BigDecimal
  val long0: BigDecimal
  val k: BigDecimal
  val dx: BigDecimal

  def transverseMercator(long: String, lat: String): (BigDecimal, BigDecimal) = {
    TransverseMercator.project(
      common.Math.toRadian(long),
      common.Math.toRadian(lat),
      this
    )
  }

  def transverseMercator(long: BigDecimal, lat: BigDecimal): (BigDecimal, BigDecimal) = {
    TransverseMercator.project(
      common.Math.toRadian(long),
      common.Math.toRadian(lat),
      this
    )
  }

  def reverseTransverseMercator(long: String, lat: String): (BigDecimal, BigDecimal) = {
    TransverseMercator.reverse(
      common.Math.toRadian(long),
      common.Math.toRadian(lat),
      this
    )
  }

  def reverseTransverseMercator(long: BigDecimal, lat: BigDecimal): (BigDecimal, BigDecimal) = {
    TransverseMercator.reverse(
      common.Math.toRadian(long),
      common.Math.toRadian(lat),
      this
    )
  }

}
