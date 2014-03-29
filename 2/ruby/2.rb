require_relative '../../lib/euler.rb'

total = 0
n     = 1
cur   = 0
while cur < 4000000 do
  if cur % 2 == 0 then total += cur end
  cur = fib(n)
  n   += 1
end

puts total
