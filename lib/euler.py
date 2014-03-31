def even(x): return x % 2 == 0

def sum(acc, x): return acc + x

def fib(n, cur = 1, prev = 0):
  if n == 0:
    return prev
  elif n == 1:
    return cur
  else:
    return fib(n - 1, cur + prev, cur)
