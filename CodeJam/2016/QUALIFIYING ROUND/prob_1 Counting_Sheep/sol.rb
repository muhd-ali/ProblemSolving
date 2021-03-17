class CountingSheep
    attr_accessor :seen, :insomnia

    def initialize
        arr = []
        for i in 0..9
            arr[i] = false
        end
        @seen = arr
        @insomnia = [0]
    end

    def allSeen?
        @seen.reduce(:&)
    end

    def add2Seen num
        dig_chars = num.to_s.split('')
        for d in dig_chars
            num = d.to_i
            @seen[num] = true
        end
    end

    def addAllMul2Seen (input_num)
        if @insomnia.include? input_num
            return 'INSOMNIA'
        end
        mul = 0
        while (not allSeen?)
            mul+=1
            num = input_num*mul
            add2Seen(num)
        end
        return num
    end
end

def count_sheep num
    sol = CountingSheep.new
    sol.addAllMul2Seen num
end
