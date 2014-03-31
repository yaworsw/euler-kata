require 'erb'
require 'nokogiri'
require 'open-uri'
require 'yaml'

class Problem

  def self.answers
    @@answers ||= YAML.load_file("#{__dir__}/../answers.yaml")
  end

  attr_accessor :id

  def initialize id
    @id  = id
    @doc = Nokogiri::HTML(open(self.url))
  end

  def answer
    Problem.answers[@id.to_i].to_s
  end

  def doc
    @doc ||= Nokogiri::HTML(open(self.url))
  end

  def prompt_node
    @prompt_node ||= doc.css('.problem_content').first
  end

  def prompt
    @prompt ||= prompt_node.inner_html
  end

  def title_node
    @title_node ||= doc.css('h2').first
  end

  def title
    @title ||= title_node.inner_html
  end

  alias_method :name, :title

  def url
    "http://projecteuler.net/problem=#{@id}"
  end

  # Readme related methods

  def readme_path
    "#{ROOT}/#{id}/README.md"
  end

  def readme_template_path
    "#{__dir__}/templates/README.md.erb"
  end

  def readme_template
    str = File.read readme_template_path
    ERB.new str
  end

  def readme_written?
    File.exists?(readme_path)
  end

  alias_method :readme_exists?, :readme_written?

  def write_readme
    args = OpenStruct.new({ problem: self })
    File.open(readme_path, 'w') do |file|
      file.write readme_template.result(args.instance_eval { binding })
    end
  end

  def write_readme_if_not_written
    write_readme unless readme_exists?
  end

  alias_method :write_readme_if_not_already_written, :write_readme_if_not_written

end
