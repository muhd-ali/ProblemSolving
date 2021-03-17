def getDivisors (input)
    divs = []
    (1..Math.sqrt(input)).each do |num|
        if input%num == 0
            divs << num
            divs << input/num
        end
    end
    return divs
end

def getRangeOfTriangularWithAtLeastNDivs(n)
    iter = 0
    num = 0
    checkAfter = 1
    prevNum = -1
    prevIter = -1
    while true
        iter += 1
        num += iter

        if (iter == checkAfter)
            # print "starting check for num #{num} \n"
            divs = getDivisors(num)

            # print divs
            if divs.length >= n
                break
            end
            # print "ending check for num #{num} \n"
            prevIter = iter
            prevNum = num
            checkAfter *= 2
        end
        # print "iter = #{iter}\n checkAfter = #{checkAfter}\n"
    end
    return [prevIter, [prevNum, num]]
end

def getTriangularWithAtLeastNDivs (n)
    rangeAnswer = getRangeOfTriangularWithAtLeastNDivs(n)
    # rangeAnswer = [67108864, [2251799847239680, 9007199321849856]]

    print "#{rangeAnswer}\n"
    iter = rangeAnswer[0]
    num = rangeAnswer[1][0]
    while true
        iter += 1
        num += iter
        print "starting check for num #{num} \n"
        divs = getDivisors(num)
        if divs.length >= n
            break
        end
    end
    return num
end

def isTriangular(n)
    iter = 0
    num = 0

    while num <= n
        iter += 1
        num += iter

        if num == n
            return true
        end
    end
    return false
end

# print "#{getTriangularWithAtLeastNDivs(500)}\n"

# found = 2251802128941651
found = 28
puts isTriangular(found)
divs = getDivisors(found)
print "#{divs}\n"
puts divs.length >= 500
