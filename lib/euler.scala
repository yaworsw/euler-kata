package euler

import scala.collection._
import scala.collection.mutable.HashMap

object euler {

  class PrimesIterator(start: Int, finish: Int) extends Iterator[Int] {
    var nextVal = if (isPrime(start)) start else nextPrime(start)

    def hasNext(): Boolean = nextVal < finish

    def next(): Int = {
      var cur = nextVal
      nextVal = nextPrime(nextVal)
      cur
    }
  }

  def even(n: BigInt): Boolean = divisor(n, 2)
  def odd (n: BigInt): Boolean = !even(n)
  def divisor(n: BigInt, x: BigInt): Boolean = n % x == 0

  def numDivisors(n: BigInt): BigInt =
    (1 /: countOccurrences(primeFactors(n))) {
      (acc, i) =>
        acc * (1 + i._2)
    }

  def unique[T](list: List[T]): Iterable[T] =
    countOccurrences(list).keys

  // Reads a file and returns the contents
  def readFile(path: String): String = {
    val source   = io.Source.fromFile(path)
    val contents = source.mkString
    source.close()
    contents
  }

  // Returns the nth number in the Fibonacci sequence
  def fib(n: Int, cur: Int = 1, prev: Int = 0): Int =
    if (n == 0) prev
    else if (n == 1) cur
    else fib(n - 1, cur + prev, cur)

  // Returns a number's prime factors
  def primeFactors(n: BigInt, divisor: Int = 2): List[Int] =
    if (n <= 1) Nil
    else if (n % divisor == 0) divisor :: primeFactors(n / divisor, divisor)
    else primeFactors(n, nextPossiblePrime(divisor))

  // Returns the lowest number greater than the given number that is not
  // divisible by 2, 3, or 5
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

  def nextPrime(n: Int): Int = {
    val possible = nextPossiblePrime(n)
    if (isPrime(possible)) possible
    else nextPrime(possible)
  }

  // Returns a prime number sieve
  def sieve(upTo: Int, cur: Int = 2): List[Int] =
    if      (cur > upTo) List()
    else if (isPrime(cur)) cur :: sieve(upTo, nextPossiblePrime(cur))
    else    sieve(upTo, nextPossiblePrime(cur))

  // Determines if a number is prime
  def isPrime(n: Int, divisor: Int = -1): Boolean = n match {
    case 2 => true
    case _ => divisor match {
      case -1 => isPrime(n, 2)
      case _  => {
        if      (n % divisor == 0)      false
        else if (divisor * divisor > n) true
        else    isPrime(n, nextPossiblePrime(divisor))
      }
    }
  }

  // Determines if the input is a palindrome by converting it into a List[Char]
  // and comparing the corresponding elements
  def palindrome[T](input: T): Boolean = {
    val list = input.toString().toList
    palindromeHelper(list.tail, list.head)
  }

  private def palindromeHelper(rest: List[Char], head: Char): Boolean =
    if      (rest.length < 1)   true
    else if (rest.last != head) false
    else    palindromeHelper(rest.tail.dropRight(1), rest.head)

  // Given a list return a map which counts how many occurrences of each
  // element is in the list
  def countOccurrences[T](list: TraversableOnce[T]): Map[T, Int] =
    list.foldLeft(new HashMap[T, Int]) {
      (acc, element) =>
        acc.update(element, acc.getOrElse(element, 0) + 1)
        acc
      }

  // Given a list of lists return a map with the count of the most times each
  // element occurs in the lists
  def highestFrequencies[T](lists: TraversableOnce[TraversableOnce[T]]): Map[T, Int] = {
    var acc = new HashMap[T, Int]()
    lists.map(countOccurrences).foldLeft(new HashMap[T, Int]) {
      (acc, map) =>
        map.foreach {
          case (key, value) =>
            val best = acc.getOrElse(key, 0)
            if (value > best) acc.update(key, value)
        }
        acc
    }
  }

  // Returns the lowest common denominator of a list of numbers
  def lcd(numbers: Seq[Int]): BigInt =
    highestFrequencies(numbers.map(BigInt.apply).map(primeFactors(_))).foldLeft(BigInt.apply(1)) {
      case (acc, (key, value)) =>
        val bigKey = BigInt.apply(key)
        acc * bigKey.pow(value)
    }

}
