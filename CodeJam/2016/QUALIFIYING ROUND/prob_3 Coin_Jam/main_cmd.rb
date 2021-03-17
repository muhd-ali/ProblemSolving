require_relative 'sol'

while true
    print 'Input Size => '
    input = gets.chomp
    words = input.split(' ')
    print getValidCoins(words[0].to_i, words[1].to_i)
    puts ''
    puts ''
end
