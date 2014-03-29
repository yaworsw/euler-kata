require 'mathn'

# Returns the nth number in the Fibonacci sequence
def fib n, cur = 1, prev = 0
  if (n == 0)
    prev
  elsif (n == 1)
    cur
  else
    fib(n - 1, cur + prev, cur)
  end
end

# Prime number sieve
def sieve up_to
  if up_to > 30
    primes = []
    cur    = 2
    while (cur <= up_to) do
      if cur.prime? then primes << cur end
      cur = cur.next_possible_prime
    end
    primes
  else
    [2, 3, 5, 7, 11, 13, 17, 23, 29].select { |x| x <= up_to }
  end
end

class Integer

  # Returns the lowest number greater than this integer that is not divisible by
  # 2, 3, and 5
  def next_possible_prime
    if (self >= 30)
      cur = self
      mod = self % 30
      if (mod == 0)
        cur -= 1
        mod  = 29
      else
        until (@@increments.has_key? mod.to_s) do
          puts mod
          mod -= 1
        end
      end
      self + @@increments[mod.to_s]
    else
      sieve(30).select { |x| x > cur }.first
    end
  end

  # Returns the prime factors for the integer
  def prime_factors
    primes  = sieve 30

    factors = []
    cur     = self

    if cur < 30
      cur_sqrt = Math.sqrt cur
      primes = primes.select { |x| x < cur_sqrt }
    end

    primes.each do |divisor|
      while (cur % divisor == 0) do
        factors << divisor
        cur /= divisor
      end
    end

    divisor = 31
    while (cur > 1) do
      while (cur % divisor == 0) do
        factors << divisor
        cur /= divisor
      end
      divisor = divisor.next_possible_prime
    end

    factors
  end

  private

    # Used to find the next number not divisible by 2, 3, or 5
    # @see Integer#next_possible_prime
    @@increments = {
      '1'  => 6,
      '7'  => 4,
      '11' => 2,
      '13' => 4,
      '17' => 2,
      '19' => 4,
      '23' => 6,
      '29' => 2
    }

end
