def prob_1(str, orgSet, alphaNum)
    set = []
    initSet = orgSet
    wordArr = str.split('')
    initSet.each do |word|
        print wordArr
        print "\n"
        add = true
        copy = wordArr.dup

        while add
            word.split('').each do |chr|
                if not (copy.include? chr)
                    add = false
                    break
                else
                    # print copy
                    copy.delete_at(copy.index(chr))
                    # print "\n" + chr + "\n"
                    # print copy
                    # print "\n"
                end
            end

            if add
                set << word
                wordArr = copy.dup
            end
        end
        print word + "\n"
        print wordArr
        print "\n\n"
    end

    print set
    print "\n"


    return set.map { |e| alphaNum[e] }.join('')

end

def solve line
    completeSet = ["ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"]
    alphaNum = {
        "ZERO" => '0',
        "ONE" => '1',
        "TWO" => '2',
        "THREE" => '3',
        "FOUR" => '4',
        "FIVE" => '5',
        "SIX" => '6',
        "SEVEN" => '7',
        "EIGHT" => '8',
        "NINE" => '9'
    }
    return prob_1(line, completeSet, alphaNum)
end

puts"==================================================\n\n\n"
print(solve("EINEENNOOINIENNNNE"))
puts"\n==================================================\n\n\n"
