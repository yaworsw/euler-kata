require_relative '../../lib/euler.rb'

best = 0

999.downto(100).each do |x|
  break if x * x < best
  x.downto(100).each do |y|
    val = x * y

    break if val < best

    best = val if (val.palindrome?)
  end
end

puts best
