class Pancakes
    attr_accessor :stack

    def initialize str
        if not isValidStr? str
            raise 'invalid string passed'
        end
        @stack = str.split('')
    end

    def isValidStr? str
        str.each_char do |ch|
            if (ch != '+' and ch != '-')
                return false
            end
        end
        true
    end

    def inverse_sign x
        if x=='+'
            return '-'
        elsif x=='-'
            return '+'
        else
            raise 'invalid sign passed'
        end
    end

    def flipNcakes n
        queue = []
        n.times do
          queue.push(inverse_sign(@stack.shift()))
        end

        n.times do
          @stack.unshift(queue.shift())
        end
    end

    def count_inversions
        count = 0
        @stack.each_with_index do |elem, i|
            next_elem = @stack[i+1].nil? ? '' : @stack[i+1]
            if next_elem != ''
                if elem != next_elem
                    count += 1
                    flipNcakes i+1
                end
            end
        end

        if @stack[0] == '-'
            count+=1
        end

        return count
    end
end

def num_moves str
    panC = Pancakes.new str
    panC.count_inversions
end
