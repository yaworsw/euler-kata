import euler._

object Main extends App {

  val answer = new euler.PrimesIterator(2, 2000000).foldLeft(BigInt.apply(0)) {
    (acc, n) =>
      acc + BigInt.apply(n)
  }

  println(answer)

}
