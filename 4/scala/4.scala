import euler._

object Main extends App {

  val Min = 100

  def findLargestPalindrome(x: Int, y: Int = 0, biggest: Int = 0): Int =
    if      (x < Min) biggest
    else if (y < Min) findLargestPalindrome(x - 1, x - 2, biggest)
    else {
      val v = x * y
      if (v < biggest)              findLargestPalindrome(x - 1, x - 2, biggest)
      else if (euler.palindrome(v)) findLargestPalindrome(x - 1, x - 2, v)
      else                          findLargestPalindrome(x    , y - 1, biggest)
    }

  val largestPalindrome = findLargestPalindrome(1000)

  println(largestPalindrome)

}
