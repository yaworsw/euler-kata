require 'colorize'

require_relative './rake/solution.rb'

ROOT = __dir__

task 'default' => ['run']

task 'run' do
  puts
  begin
    puts Solution.from_path().run
  rescue Exception => ex
    puts ex.to_s.on_red
  end
  puts
end

task 'test' do
  solution = Solution.from_path()
  problem  = solution.problem

  puts
  puts "Testing #{solution.language} solution to"
  puts "##{problem.id} - #{problem.name}".bold
  puts
  puts "Expected: " + problem.answer.cyan
  puts "Result:   " + solution.result.cyan
  puts

  if solution.correct?
    puts "                         PASS                         ".bold.on_green
  else
    puts "                         FAIL                         ".bold.on_red
  end

  puts
end

task 'new', [:problem_id, :language] do |t, options|
  problem_id  = options.problem_id
  language    = options.language

  solution = Solution.new problem_id, language
  problem  = solution.problem

  solution.prep

  puts
  puts "Done initializing empty #{language} solution for"
  puts "##{problem.id} - #{problem.name}".bold
  puts
end
