func sum(numbers: Array<Int>) -> Int {
  return reduce(numbers, 0) { $0 + $1 }
}

let numbers = filter(1..<1000) { $0 % 3 == 0 || $0 % 5 == 0 }

println(sum(numbers))

