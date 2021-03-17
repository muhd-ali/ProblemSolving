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


while true
    print 'Input String => '
    input = gets.chomp
    print "number of valid words: #{num_possibilities(input)%1000000007}"
    puts ' '
    puts ' '
end
