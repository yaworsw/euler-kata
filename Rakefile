require 'colorize'

require_relative './rake/solution.rb'

ROOT = __dir__

task 'default' => ['run']

desc 'Run a solution to a problem.  Will attem'
task 'run', [:problem_id, :language] do |t, options|
  begin
    puts
    puts Solution.from_options_or_path(options).run
    puts
  rescue Exception => ex
    puts ex.to_s.on_red
  end
end

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

task 'new', [:problem_id, :language] do |t, options|
  solution = Solution.from_options(options)
  problem  = solution.problem

  solution.prep

  puts
  puts "Done initializing empty #{language} solution for"
  puts "##{problem.id} - #{problem.name}".bold
  puts
end

task 'desc', [:problem_id] do |t, options|
  problem_id = options.problem_id
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
