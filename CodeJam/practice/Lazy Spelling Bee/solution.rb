def find_combs(word, lo, hi, combs)
    if word.length == 1
        combs << 1
        return combs
    elsif hi - lo < 0
        combs << 1
        return combs
    elsif lo == 0
        if (word[lo] != word[lo+1])
            combs << 2
        else
            combs << 1
        end
    elsif lo == hi
        if (word[lo] != word[lo-1])
            combs << 2
        else
            combs << 1
        end
    else
        if (word[lo] != word[lo+1] and word[lo] != word[lo-1] and word[lo-1] != word[lo+1])
            combs << 3
        elsif (word[lo] != word[lo+1])
            combs << 2
        elsif (word[lo] != word[lo-1])
            combs << 2
        else
            combs << 1
        end
    end

    find_combs(word, lo+1, hi, combs)
end


def num_possibilities(word)
    arr = find_combs(word, 0, word.length-1, [])
    arr.reduce(:*)
end


def read_and_write(args)
    num_tests = -1
    File.open(args[0], "r") do |f|
        File.open(args[1], 'w') { |file|
            count = 0
            f.each_line do |line|
                if count == 0
                    num_tests = (line.chomp).to_i
                else
                    line = line.chomp
                    # puts("Case ##{count}: #{num_possibilities(line)%1000000007}\n")
                    file.write("Case ##{count}: #{num_possibilities(line)%1000000007}\n")
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
