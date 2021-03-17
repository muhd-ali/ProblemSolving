def last_word str
    word = []
    first_chr = ''
    str.each_char { |chr|
        if (chr >= first_chr)
            word.unshift(chr)
            first_chr = chr
        else
            word.push(chr)
        end
    }
    return word.join()
end
