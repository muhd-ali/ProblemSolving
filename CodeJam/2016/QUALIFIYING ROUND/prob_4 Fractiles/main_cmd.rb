require_relative 'sol'

while true
    print 'Input Size => '
    input = gets.chomp
    words = input.split(' ')
    # print Prime.prime_division(input.to_i)
    print getValidCoins(words[0].to_i, words[1].to_i)
    # cJam.noPrime?
    puts ''
    puts ''
end
