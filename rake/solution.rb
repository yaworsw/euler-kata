require 'fileutils'

require_relative './problem.rb'

class Solution

  def self.all
    Problem.all.map { |p| p.solutions }.flatten
  end

  def self.from_options options
    if options.problem_id.nil? or options.language.nil?
      raise "Insufficient options parameters (#{options})"
    else
      Solution.new options.problem_id, options.language
    end
  end

  def self.from_options_or_path options
    if options.problem_id.nil? or options.language.nil?
      Solution.from_path
    else
      Solution.new options.problem_id, options.language
    end
  end

  def self.from_path path = ENV['cwd']
    begin
      problem_id  = Regexp.new("#{ROOT}/(\\d)").match(path)[1]
      language_id = Regexp.new("#{ROOT}/\\d/([^/]+)").match(path)[1]
      Solution.new problem_id, language_id
    rescue
      raise "Unable to distinguish problem or language id form path (#{path})"
    end
  end

  attr_accessor :problem, :language

  def initialize problem_id, language
    @problem  = Problem.new problem_id
    @language = language
  end

  def dir
    "#{ROOT}/#{problem.id}/#{language}/"
  end

  def problem_id
    problem.id
  end

  alias_method :pid, :problem_id

  def prep
    mkdir
    problem.write_readme_if_not_written
    self.send "prep_#{language}"
    self
  end

  def run
    @result ||= self.send("run_#{language}").sub(/\s+$/,'') # remove any trailing whitespace
  end

  alias_method :result, :run

  def correct?
    self.run == problem.answer
  end

  #
  # Conversion methods
  #

  def to_s
    "#{language} solution for #{problem}"
  end

  protected

    def mkdir
      FileUtils::mkdir_p dir
      self
    end

  private

    #
    # Python
    #

    def run_python
      `python #{ROOT}/#{problem_id}/python/#{problem_id}.py`
    end

    def prep_python
      FileUtils.cp "#{ROOT}/rake/templates/new.py", "#{ROOT}/#{pid}/python/#{pid}.py"
    end

    #
    # Ruby
    #

    def run_ruby
      problem_id = @problem.id
      `ruby #{ROOT}/#{problem_id}/ruby/#{problem_id}.rb`
    end

    def prep_ruby
      problem_id = @problem.id
      FileUtils.cp "#{ROOT}/rake/templates/new.rb", "#{ROOT}/#{pid}/ruby/#{pid}.rb"
    end

    #
    # Scala
    #

    def run_scala
      problem_id = @problem.id
      Dir.chdir "#{ROOT}/#{problem_id}/scala/"
      `scalac #{ROOT}/#{problem_id}/scala/*.scala && scala Main`
    end

    def prep_scala
      FileUtils.symlink "#{ROOT}/lib/euler.scala", "#{ROOT}/#{pid}/scala/euler.scala"
      FileUtils.cp "#{ROOT}/rake/templates/new.scala", "#{ROOT}/#{pid}/scala/#{pid}.scala"
    end

end
