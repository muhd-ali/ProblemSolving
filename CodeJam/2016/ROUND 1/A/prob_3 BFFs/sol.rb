def get_friendships (str)
    arr = str.split(' ').map { |e| e.to_i  }
    edge = {}
    count = 1
    arr.each { |e|
        edge[count] = e
        count+=1
    }
    return edge
end

def explore_kid(hashMap, kid)
    kids = []
    kids << kid
    print hashMap
    puts ' '
    while true
        nextKid = hashMap[kid]
        hashMap[kid] = -1

        print "hashMap[#{kid}] = #{nextKid}\n\n"

        if (nextKid == nil || nextKid == -1)
            break
        else
            if (not kids.include? nextKid) and hashMap[nextKid]!=-1
                kids << nextKid
            end
        end

        print "#{kids} at #{kid}\n\n"
        kid = nextKid
    end

    if isValid(kids, hashMap)
        return kids.length
    else
        return 0
    end
end

def isValid(arr, hashMap)
    for i in 1..hashMap.length
        if (i-1 > -1 and arr[i-1] != hashMap[i]) and (i+1 < arr.length and arr[i+1] != hashMap[i])
            return false
        end
    end
    return true
end

def max_circle str
    friendships = get_friendships(str)
    print friendships
    circles = []
    for i in 1..friendships.length
        num = explore_kid(friendships.dup, i)
        circles << num
    end
    return circles.max
end

puts explore_kid((get_friendships '3 3 4 1'),2)
