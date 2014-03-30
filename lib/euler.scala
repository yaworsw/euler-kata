package euler

import scala.collection._
import scala.collection.mutable.HashMap

object euler {

  def fib(n: Int, cur: Int = 1, prev: Int = 0): Int =
    if (n == 0) prev
    else if (n == 1) cur
    else fib(n - 1, cur + prev, cur)

  def primeFactors(n: BigInt, divisor: Int = 2): List[Int] = {
    if (n <= 1) Nil
    else if (n % divisor == 0) divisor :: primeFactors(n / divisor, divisor)
    else primeFactors(n, nextPossiblePrime(divisor))
  }

  private val primesUnder30 = List(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
  def nextPossiblePrime(n: Int): Int = {
    if (n < 29) primesUnder30.find(x => x > n).get
    else {
      val mod = n % 30
      if      (mod < 6)   n + 7 - mod
      else if (mod == 29) n + 2
      else if (mod == 0)  n + 1
      else n - (n % 30) + nextPossiblePrime(n % 30)
    }
  }

  def sieve(upTo: Int, cur: Int = 2): List[Int] =
    if      (cur > upTo) List()
    else if (isPrime(cur)) cur :: sieve(upTo, nextPossiblePrime(cur))
    else    sieve(upTo, nextPossiblePrime(cur))

  def isPrime(n: Int, divisor: Int = -1): Boolean = divisor match {
    case -1 => isPrime(n, 2)
    case _  => {
      if      (n == 2)                true
      else if (n % divisor == 0)      false
      else if (divisor * divisor > n) true
      else    isPrime(n, nextPossiblePrime(divisor))
    }
  }

  private def palindromeHelper(rest: List[Char], head: Char): Boolean =
    if      (rest.length < 1)   true
    else if (rest.last != head) false
    else    palindromeHelper(rest.tail.dropRight(1), rest.head)

  def palindrome(input: Any): Boolean = {
    val list = input.toString().toList
    palindromeHelper(list.tail, list.head)
  }

  // Given a list of lists calculate the frequencies the elements appear in
  // those lists
  def frequency[T](lists: TraversableOnce[TraversableOnce[T]]): Map[T, Int] =
    lists.foldLeft(new HashMap[T, Int]) {
      (acc, elements) =>
        elements.foldLeft(new HashMap[T, Int]) {
          (acc, element) =>
            acc.update(element, acc.getOrElse(element, 0) + 1)
            acc
        }.foreach {
          case (key, value) =>
            val best = acc.getOrElse(key, 0)
            if (value > best) acc.update(key, value)
        }
        acc
    }

  def lcd(numbers: Seq[Int]): BigInt =
    frequency(numbers.map(BigInt.apply).map(primeFactors(_))).foldLeft(BigInt.apply(1)) {
      case (acc, (key, value)) =>
        val bigKey = BigInt.apply(key)
        acc * bigKey.pow(value)
    }

}
