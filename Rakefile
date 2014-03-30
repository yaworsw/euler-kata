require 'fileutils'

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

task 'new', [:id, :lang] do |t, options|
  lang = options.lang
  pid  = options.id

  puts "#{__dir__}/#{pid}/#{lang}/"

  FileUtils::mkdir_p "#{__dir__}/#{pid}/#{lang}/"

  if lang == 'ruby'
    FileUtils.cp "#{__dir__}/rake/templates/new.rb", "#{__dir__}/#{pid}/ruby/#{pid}.rb"
  elsif lang == 'scala'
    FileUtils.symlink "#{__dir__}/lib/euler.scala", "#{__dir__}/#{pid}/scala/euler.scala"
    FileUtils.cp "#{__dir__}/rake/templates/new.scala", "#{__dir__}/#{pid}/scala/#{pid}.scala"
  end
end
