Euler.register_language('swift', Class.new do

  def run solution
    `swift #{file_path(solution)}`
  end

  # Copy the template into the solution's directory
  def init solution
    FileUtils.touch(file_path(solution))
  end

  private

    def file_path solution
      "#{solution.dir}/#{solution.problem.id}.swift"
    end

end)
