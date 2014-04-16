require_relative '../../lib/euler.rb'

ones = [
  'one',
  'two',
  'three',
  'four',
  'five',
  'six',
  'seven',
  'eight',
  'nine'
]

tens = [
  'ten',
  'eleven',
  'twelve',
  'thirteen',
  'fourteen',
  'fifteen',
  'sixteen',
  'seventeen',
  'eighteen',
  'nineteen',
  'twenty',
  ones.map { |n| "twenty #{n}" },
  'thirty',
  ones.map { |n| "thirty #{n}" },
  'forty',
  ones.map { |n| "forty #{n}" },
  'fifty',
  ones.map { |n| "fifty #{n}" },
  'sixty',
  ones.map { |n| "sixty #{n}" },
  'seventy',
  ones.map { |n| "seventy #{n}" },
  'eighty',
  ones.map { |n| "eighty #{n}" },
  'ninety',
  ones.map { |n| "ninety #{n}" }
].flatten

hundreds = ones.map do |n|
  ["#{n} hundred"] << [ones, tens].flatten.map{ |t| "#{n} hundred and #{t}"}
end.flatten

all = [ones, tens, hundreds].flatten << 'one thousand'

puts all.map{ |n| n.gsub(/[ ]/, '').size }.reduce(:+)
