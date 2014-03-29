import euler._

object Main extends App {

  def fibSum(sumWhen: Int => Boolean, stopWhen: Int => Boolean, sum: Int = 0, cur: Int = 0): Int = {
    var curVal = euler.fib(cur)
    if (stopWhen(curVal)) sum
    else
      if (sumWhen(curVal)) fibSum(sumWhen, stopWhen, sum + curVal, cur + 1)
      else fibSum(sumWhen, stopWhen, sum, cur + 1)
  }

  val sum = fibSum(
    sumWhen  = (x) => x % 2 == 0,
    stopWhen = (x) => x > 4000000
  )

  println(sum)

}
