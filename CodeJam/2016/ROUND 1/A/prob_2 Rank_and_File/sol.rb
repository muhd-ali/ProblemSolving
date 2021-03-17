def rank_and_file (str)
    grid = []
    arr = arr.sort {|a,b| a[0] <=> b[0]}
    grid << arr[0]
    num = arr[0][0]
    count = 0
    limit = 2*n-2
    for i in 0..limit
        if num != arr[i][0]
            num = arr[i][0]
            grid << arr[i]
            count+=1
        end

        if count == n
            break
        end
    end
    return grid
end

print rank_and_file([
    [1, 2, 3],
    [2, 3, 5],
    [3, 5, 6],
    [2, 3, 4],
    [1, 2, 3]
], 3)
