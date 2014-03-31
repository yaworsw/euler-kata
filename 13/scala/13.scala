import euler._

object Main extends App {

  val input = euler.readFile("../in").split("\n")

  val answer = (BigInt.apply(0) /: input.map(BigInt.apply))(_+_).toString().substring(0, 10)

  println(answer)

}
