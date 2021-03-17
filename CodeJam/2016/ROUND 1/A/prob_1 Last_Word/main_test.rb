require_relative 'sol'

test_input = 0
anss = []

print_after = 10e3
max_range = 10e6
parts_remaining = max_range/print_after
while test_input <= max_range
    sol = CountingSheep.new
    ans = sol.addAllMul2Seen test_input
    anss << ans
    test_input+=1

    if test_input%print_after == 0
        parts_remaining -= 1
        puts "#{parts_remaining} parts remaining"
    end
end
