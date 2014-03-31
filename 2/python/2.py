import os
import sys

sys.path.append(os.path.realpath(os.path.dirname(__file__) + '/../../lib'))

euler = __import__('euler')

def fibSequence(start, maximum):
  i   = start
  cur = euler.fib(i)
  while cur < maximum:
    yield cur
    i += 1
    cur = euler.fib(i)

even_fib_terms = filter(euler.even, fibSequence(2, 4000000))

answer = reduce(euler.sum, even_fib_terms)

print(answer)
