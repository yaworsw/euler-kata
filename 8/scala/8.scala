import euler._

object Main extends App {

  val input = euler.readFile("../in").toList.map(c => c.asDigit)

  val answer = input.sliding(5).foldLeft(0) {
    (acc, cur) =>
      val product = cur.foldLeft(1)(_*_)
      if (acc < product) product
      else acc
   }

  println(answer)

}
