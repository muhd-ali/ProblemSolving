def neighbours(grid2D, coord)
    neigh = []
    i = coord[0]
    j = coord[1]

    if (i > 0)
        neigh << [i-1,j]
    end
    if (j > 0)
        neigh << [i, j-1]
    end
    if (i+1 < grid2D.length)
        neigh << [i+1, j]
    end
    if (j+1 < grid2D[i].length)
        neigh << [i, j+1]
    end

    return neigh
end

def run_DFS(grid, vertex)
    grid[vertex[0]][vertex[1]] = '0'
    neigh = neighbours(grid, vertex)

    for v in neigh
        if grid[v[0]][v[1]] == '1'
            run_DFS(grid, v)
        end
    end

    return grid
end

def count_connected_worker(grid, input_zero_here)
    for i in 0..grid.length-1
        for j in 0..grid[i].length-1
            if (grid[i][j] == '1')
                input_zero_here +=1
                grid = run_DFS(grid, [i,j])
                return count_connected_worker(grid, input_zero_here)
            end
        end
    end
    return input_zero_here
end

def count_connected_wrapper(grid)
    count_connected_worker(grid, 0)
end

def read_grid(file_descriptor, num_lines)
    count = 0
    grid = []
    puts ">>>#{num_lines}"
    file_descriptor.each_line do |line|
        line = line.chomp
        print line
        puts ' '

        count += 1
        if count == num_lines
            return file_descriptor
        end
    end
end

def read_test(file_descriptor)
    count = 0
    file_descriptor.each_line do |line|
        line = line.chomp
        if count == 0
            dims = line.split(' ')
            num_rows = dims[0].to_i
            num_cols = dims[1].to_i
        elsif count == 1
            file_descriptor = read_grid(file_descriptor, num_rows)
            return
        end


        count+=1
    end
    return file_descriptor
end

def read_and_write(args)
    num_tests = -1
    File.open(args[0], "r") do |f|
        File.open(args[1], 'w') { |file|
            count = 0

            f.each_line do |line|
                line = line.chomp
                if count == 0
                    num_tests = (line).to_i
                else
                    f = read_test(f)
                    return
                end

                count+=1
            end
        }
    end
end

args = ARGV
read_and_write(args)
