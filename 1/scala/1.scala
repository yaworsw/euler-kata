import euler._

object Main extends App {

  type Set = Int => Boolean

  val maximum = 999

  def reduce(s: Set, res: Int = 0, cur: Int = 0): Int =
    if (cur > maximum) res
    else if (s(cur)) reduce(s, res + cur, cur + 1)
    else reduce(s, res, cur + 1)

  def set(x: Int): Boolean =
    x % 3 == 0 || x % 5 == 0

  println(reduce(set))

}
