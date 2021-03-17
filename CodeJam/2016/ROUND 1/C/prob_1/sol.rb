def prob_1 (str, letters)
    senators = str.split(' ').map {|e|  e.to_i}
    maxMems = senators.max
    minMems = senators.min
    sol = []
    while (maxMems != minMems)
        i = senators.index(maxMems)
        first = second = ''
        if (maxMems - 1 >= minMems)
            first = letters[i]
            senators[i] -= 1
        end

        maxMems = senators.max
        i = senators.index(maxMems)
        if (maxMems - 1 >= minMems)
            second = letters[i]
            senators[i] -= 1
        end
        maxMems = senators.max

        sol << first + second
    end

    if (senators.length%2 == 0)
        while (maxMems != 0)
            for i in (0 .. senators.length-2).step(2)
                sol <<  letters[i] + letters[i+1]
                senators[i] -= 1
                senators[i+1] -= 1
            end
            maxMems = senators.max
        end
    else
        while (maxMems != 0)
            for i in (0 .. senators.length-3)
                sol <<  letters[i]
                senators[i] -= 1
            end
            i = senators.length-2
            sol <<  letters[i] + letters[i+1]
            senators[i] -= 1
            senators[i+1] -= 1

            maxMems = senators.max
        end
    end

    return sol.join(' ')
end

# print prob_1('1 3 4', letters)

def solve line
    letters = ('A'..'Z').map { |e| e }
    return prob_1(line, letters)
end
