import euler._

object Main extends App {

  def fibSum(sum: Int = 0, cur: Int = 1, prev: Int = 1, until: Int = 100, when: (Int) => Boolean = x =>  true): Int =
    if       (cur > until) sum
    else if  (when(cur))   fibSum(sum + cur, prev + cur, cur, until, when)
    else                   fibSum(sum      , prev + cur, cur, until, when)

  val answer = fibSum(until = 4000000, when = x => x % 2 == 0)

  println(answer)

}
