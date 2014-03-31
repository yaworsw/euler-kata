import euler._

object Main extends App {

  def findFirstTriangularNumberWithXDivisors(x: Int, i: Int = 1, cur: BigInt = 0): BigInt =
    if   (euler.numDivisors(cur) >= x) cur
    else findFirstTriangularNumberWithXDivisors(x, i + 1, cur + i)

  val answer = findFirstTriangularNumberWithXDivisors(500)

  println(answer)

}
