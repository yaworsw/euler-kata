import euler._

object Main extends App {

  val factors = euler.primeFactors(BigInt.apply("600851475143"))

  println(factors.max)

}
