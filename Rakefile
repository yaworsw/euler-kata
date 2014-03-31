require 'colorize'
require 'fileutils'
require 'net/http'
require 'yaml'
require 'nokogiri'
require 'open-uri'

task 'default' => ['run']

def get_problem_description problem_id
  url   = "http://projecteuler.net/problem=#{problem_id}"
  doc   = Nokogiri::HTML(open("http://projecteuler.net/problem=#{problem_id}"))
  {
    title:           doc.css('h2').first.inner_html,
    problem_content: doc.css('.problem_content').first.inner_html,
    url:             url
  }
end

def get_readme_path problem_id
  "#{__dir__}/#{problem_id}/README.md"
end

def write_readme problem_id
  path = get_readme_path problem_id
  return if File.exists?(path)

  desc = get_problem_description problem_id
  File.open(path, 'w') do |file|
    file.write "# [#{desc[:title]}](#{desc[:url]})\n\n#{desc[:problem_content]}"
  end
end

def problem_id_from_path path
  Regexp.new("#{__dir__}/(\\d)").match(path)[1]
end

def language_form_path path
  Regexp.new("#{__dir__}/\\d/([^/]+)").match(path)[1]
end

def run problem_id, lang
  if lang == 'ruby'
    `ruby #{__dir__}/#{problem_id}/ruby/#{problem_id}.rb`

  elsif lang == 'scala'
    Dir.chdir "#{__dir__}/#{problem_id}/scala/"
    `scalac #{__dir__}/#{problem_id}/scala/*.scala && scala Main`

  end
end

task 'run' do
  cwd = ENV['cwd']

  problem_id = problem_id_from_path cwd
  lang       = language_form_path cwd

  puts run problem_id, lang
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

  write_readme pid
end

task 'test' do
  cwd = ENV['cwd']

  problem_id = problem_id_from_path cwd
  lang       = language_form_path cwd

  puts
  puts "Testing #{lang} answer for problem #{problem_id}."

  answers = YAML.load_file "#{__dir__}/answers.yaml"

  correct_answer = answers[problem_id.to_i].to_s

  puts
  puts ("Expected: ".light_black + "#{correct_answer}").bold

  result  = (run problem_id, lang).sub(/\s$/,'')

  puts ("Result:   ".light_black + "#{result}").bold

  puts
  if result == correct_answer
    puts "                         PASS                         ".bold.on_green
  else
    puts "                         FAIL                         ".bold.on_red
  end
  puts
end
