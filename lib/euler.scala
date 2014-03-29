package euler

object euler {

  def fib(n: Int, cur: Int = 1, prev: Int = 0): Int =
    if (n == 0) prev
    else if (n == 1) cur
    else fib(n - 1, cur + prev, cur)

}
