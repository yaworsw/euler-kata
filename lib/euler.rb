def fib n, cur = 1, prev = 0
  if (n == 0)
    prev
  elsif (n == 1)
    cur
  else
    fib(n - 1, cur + prev, cur)
  end
end
