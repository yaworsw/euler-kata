package euler

object euler {

  def fib(n: Int, cur: Int = 1, prev: Int = 0): Int =
    if (n == 0) prev
    else if (n == 1) cur
    else fib(n - 1, cur + prev, cur)

  def primeFactors(n: BigInt, divisor: Int = 2): List[Int] =
    if (n <= 1) Nil
    else if (n % divisor == 0) divisor :: primeFactors(n / divisor, divisor)
    else primeFactors(n, nextPossiblePrime(divisor))

  val primesUnder30 = List(1, 2, 3, 5, 7, 11, 13, 17, 23, 29)
  def nextPossiblePrime(n: Int): Int =
    if (n < 29) primesUnder30.find(x => x > n).get
    else if (n == 29) n + 2
    else if (n % 30 == 0) n + 1
    else n - (n % 30) + nextPossiblePrime(n % 30)

  def sieve(upTo: Int): List[Int] =
    if (upTo <= 2) List(2)
    else if (isPrime(upTo)) sieve(upTo - 2) :+ upTo // decrement by 2 since all primes are odd
    else sieve(upTo - 1)

  def isPrime(n: Int, divisor: Int = -1): Boolean = divisor match {
    case -1 => isPrime(n, 2)
    case _  => {
      if (n % divisor == 0) false
      else if (divisor * divisor > n) true
      else isPrime(n, nextPossiblePrime(divisor))
    }
  }

}
