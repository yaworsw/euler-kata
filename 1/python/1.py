import euler

def multiple_of_three(x): return x % 3 == 0
def multiple_of_five(x):  return x % 5 == 0

def multiple_of_three_or_five(x): return multiple_of_three(x) or multiple_of_five(x)

multiples_of_three_or_five = filter(multiple_of_three_or_five, range(1, 1000))

answer = reduce(euler.sum, multiples_of_three_or_five)

print(answer)
