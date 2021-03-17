# require_relative 'sol'

def gen_sq(size)
    genAllBinStrIterative(size){|val|
        val = val.gsub('0', 'G')
        val = val.gsub('1', 'L')

        # puts val
        puts "#{val} #{get_next_comp_of(val, val)} #{get_next_comp_of(get_next_comp_of(val,val), val)}"
        # print 'press enter'
        # gets
        # puts ''
        # puts ''

    }
end

def get_next_comp_of(str, original_str)
    arr = []
    str.each_char { |chr|
        if chr=='G'
            arr << genNCharStr(original_str.length, 'G')
        elsif chr=='L'
            arr << original_str
        else
            raise 'invalid chracter passed'
        end
    }
    return arr.join
end

def genAllBinStrIterative(const_size)
    for i in 0..(2**const_size)
        str = i.to_s(2)
        if str.length <= const_size
            str = genNCharStr(const_size-str.length, '0') + str
            yield str
        else
            break
        end
    end
end

def genNCharStr(n, chr)
    str = ''
    n.times do
        str+=chr
    end
    return str
end

gen_sq(1)
puts '=========================================================='
gen_sq(2)
puts '=========================================================='
gen_sq(3)
puts '=========================================================='
gen_sq(4)
puts '=========================================================='
gen_sq(5)
puts '=========================================================='
