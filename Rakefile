require 'colorize'
require 'peach'

require_relative './rake/solution.rb'

ROOT = __dir__

task 'default' => ['run']

desc 'Run a solution to a problem.'
task 'run', [:problem_id, :language] do |t, options|
  puts
  begin
    puts Solution.from_options_or_path(options).run
  rescue Exception => ex
    puts ex.to_s.on_red
  end
  puts
end

desc 'Test a solution to see if it is correct.'
task 'test', [:problem_id, :language] do |t, options|
  begin
    solution = Solution.from_options_or_path(options)
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
  rescue Exception => ex
    puts ex.to_s.on_red
  end
end

desc 'Tests all solutions to make sure they are all correct.'
task 'test_all' do
  failures = []

  puts
  puts "Testing all solutions."
  puts
  Solution.all.peach do |solution|
    if solution.correct?
      print ".".green
    else
      failures << solution
      print "X".bold.red
    end
  end
  puts
  puts
  if failures.empty?
    print "                   ".bold.on_green
    print "All TESTS PASSED".bold.on_green
    puts  "                   ".bold.on_green
  else
    print "                 ".bold.on_red
    print "THERE WERE #{failures.length} FAILURE#{failures.length != 1 ? 'S' : ''}".bold.on_red
    puts  "                 ".bold.on_red
    failures.each do |failure|
      puts
      puts failure.to_s.bold
      puts "Expected: " + failure.problem.answer.cyan
      puts "Result:   " + failure.result.cyan
    end
  end
  puts

  fail if not failures.empty?
end

desc 'Initialize a new empty solution.'
task 'new', [:problem_id, :language] do |t, options|
  solution = Solution.from_options(options)
  problem  = solution.problem

  solution.prep

  puts
  puts "Done initializing empty #{language} solution for"
  puts "##{problem.id} - #{problem.name}".bold
  puts
end

desc 'Describe a problem.'
task 'desc', [:problem_id] do |t, options|
  problem_id = options.problem_idc

  problem    = Problem.new problem_id

  puts
  puts "##{problem.id} - #{problem.name}".bold
  puts

  problem.prompt_node.css('p').each do |p|
    puts p.inner_html
    puts
  end

  if problem.has_answer?
    puts "The solution to this problem is: " + problem.answer.bold
  else
    puts "This problem does not have an answer yet.".on_red
  end
  puts
end
