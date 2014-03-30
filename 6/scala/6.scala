import euler._

object Main extends App {

  val sum    = (acc: Int, x: Int) => acc + x
  val square = (x: Int) => x * x

  val sumOfSquares = (1 to 100).map(square).foldLeft(0)(sum)
  val squareOfSums = square((1 to 100).foldLeft(0)(sum))

  println(squareOfSums - sumOfSquares)

}
