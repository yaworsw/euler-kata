import euler._

object Main extends App {

  val answer = (1 until 10001).foldLeft(2) {
    (acc, _) =>
      euler.nextPrime(acc)
  }

  println(answer)

}
