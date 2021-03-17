def prob_2 (str)
    inputArr = str.split(' ').map { |e| e.to_i }
    valK = inputArr[3]
    clothes = inputArr[0..2]

    temp = []
    count3 = 0
    for i in (1 .. clothes[0])
        count1 = 0
        for j in (1 .. clothes[1])
            count2 = 0
            for k in (1 .. clothes[2])
                if (count3 == valK and i == k)
                    break
                end

                count1 += 1
                count2 += 1
                temp <<  i.to_s + " " + j.to_s + " " + k.to_s

                if (i == k)
                    count3 += 1
                end

                if (count1 == valK || count2 == valK)
                    break
                end
            end
        end
    end

    sol = []
    sol << temp.length

    temp.each { |e| sol << e }

    sol = sol.join("\n")
    return sol
end

# print(prob_2('1 3 3 2'), "\n")
