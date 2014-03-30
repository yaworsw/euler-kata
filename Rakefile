task 'default' => ['run']

task 'run' do
  cwd = ENV['cwd']

  problem_id = Regexp.new("#{__dir__}/(\\d)").match(cwd)[1]
  lang       = Regexp.new("#{__dir__}/\\d/([^/]+)").match(cwd)[1]

  if lang == 'ruby'
    exec "ruby #{__dir__}/#{problem_id}/ruby/#{problem_id}.rb"
  elsif lang == 'scala'
    Dir.chdir "#{__dir__}/#{problem_id}/scala/"
    exec "scalac #{__dir__}/#{problem_id}/scala/*.scala && scala Main"
  end

end
