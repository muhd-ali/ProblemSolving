require_relative 'sol'

def read_and_write(args)
    if args.length != 1
        raise "usage:\n\n ---> ruby main_file.rb <input_filename> \n\n"
    end

    num_tests = -1
    input_filename = args[0]
    File.open(input_filename, "r") do |f|
        output_filename = input_filename.split('.')[0] + '.out'
        File.open(output_filename, 'w') { |file|
            count = 0
            f.each_line do |line|
                line = line.chomp
                if count == 0
                    num_tests = line.to_i
                else
                    str = "Case ##{count}: #{count_sheep line.to_i}\n"
                    file.write(str)
                end

                if count == num_tests
                    break
                end

                count+=1
            end
        }
    end
end


args = ARGV
read_and_write(args)
