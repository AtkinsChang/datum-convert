package edu.nccu.plsm.geo.common

import java.math.{MathContext, RoundingMode}

/**
 * @version
 * @since
 */
object Math {

  /**
   * Pi in BigDecimal in precision 100
   */
  lazy val bigPi = BigDecimal(
    """3.141592653589793238462643
      |3832795028841971693993751
      |0582097494459230781640628
      |6208998628034825342117067
      |""".stripMargin.replaceAll("\n", ""),
    new MathContext(100, RoundingMode.HALF_EVEN)
  )

  private[this] val DegreeRex = """^([0-9]+)[°d]([0-9]+)['m]([0-9.]+)["s]$""".r

  /**
   * BigDecimal square root
   *
   * @param n
   * @return
   */
  def sqrt(n: BigDecimal): BigDecimal = {
    val maxIterations = n.mc.getPrecision + 1

    val guessSteam: Stream[BigDecimal] = newtonRaphson(n).take(maxIterations)
    val exactMatch: Option[Stream[BigDecimal]] = guessSteam.sliding(2).find(a => a(0) == a(1))
    val squareRoot: Stream[BigDecimal] = exactMatch.getOrElse(Stream(guessSteam.last))

    squareRoot(0)
  }

  /**
   * Degree to radian
   *
   * ex.
   * 121°01'23.123"
   * 121d01m23.123s
   * 121.0064...
   *
   * @param degree
   * @return
   */
  def toRadian(degree: String): BigDecimal = {
    (
      degree.replaceAll(" ", "").trim match {
        case DegreeRex(d, m, s) =>
          BigDecimal(d) +
            BigDecimal(m) / 60 +
            BigDecimal(s) / 60 / 60
        case degree: String =>
          BigDecimal(degree)
      }
      ) * bigPi / 180
  }

  /**
   * Numeric degree to radian
   *
   * @param degree
   * @return
   */
  def toRadian(degree: BigDecimal): BigDecimal = {
    degree * bigPi / 180
  }

  /**
   * Get a lazy stream represent the series of exponential base on n
   *
   * @param n
   * @return
   */
  def lazyExponential(n: BigDecimal): Stream[BigDecimal] = {
    lazyExponential(BigDecimal(1), n)
  }

  private[this] def newtonRaphson(toSqrt: BigDecimal, guess: BigDecimal): Stream[BigDecimal] = {
    Stream.cons(guess, newtonRaphson(toSqrt, ((toSqrt / guess) + guess) / 2))
  }

  private[this] def newtonRaphson(toSqrt: BigDecimal): Stream[BigDecimal] = {
    newtonRaphson(toSqrt, toSqrt / 2)
  }

  private[this] def lazyExponential(init: BigDecimal, n: BigDecimal): Stream[BigDecimal] = {
    Stream.cons(init, lazyExponential(init * n, n))
  }

}
