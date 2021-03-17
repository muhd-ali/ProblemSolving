class AsyncTest
    def generator(max, &block)
        if max == 0
            return
        end
        generator(max-1, &block)
        yield max, block
    end

    def co(max)
        count = 0
        generator(max) do |val|
            count += 1
            puts count
            puts 'here'

            if count == 3
                break
            end

        end
    end
end

at = AsyncTest.new
at.co 5
