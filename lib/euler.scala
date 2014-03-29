package euler

object euler {

  def fib(x: Int): Int =
    if (x < 3) 1
    else fib(x - 1) + fib(x - 2)

}
