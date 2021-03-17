require 'Prime'

class CoinJam
    attr_accessor :base

    def self.genValidCoins(n, j)
        coins = []
        proof = []
        count = 0
        genAllBinStrIterative(n-2){ |val|
            val = '1'+val+'1'
            if isValidCoin? val
                if not coins.include?(val)
                    puts 'found a coin'
                    puts "#{j-count-1} remaining"
                    coins << val
                    proof << extract_one_factors(@base)
                    count+=1
                end
            end

            if count == j
                break
            end
        }

        return [coins,proof]
    end

    def self.extract_one_factors bHash
        # factors = {}
        factors = []
        for i in 2..10
            # factors[i] = [bHash["base #{i}"][0], bHash["base #{i}"][1][0][0]]
            factors[i-2] = bHash["base #{i}"][1][0]
        end
        return factors
    end

    # def self.genAllBinStrRecursive(const_size, size, &block)
    #     if size==0
    #         return ['']
    #     end
    #
    #     lower = genAllBinStrRecursive(const_size, size-1, &block)
    #     higher = []
    #     lower.each do |word|
    #         str0 = genNCharStr(const_size-size+1, '0') + word
    #         yield str0, block
    #         higher << '0'+word << '1'+word
    #     end
    #     return higher
    # end

    def self.genAllBinStrIterative(const_size)
        for i in 0..(2**const_size)
            str = i.to_s(2)
            if str.length <= const_size
                str = genNCharStr(const_size-str.length, '0') + str
                yield str
            else
                break
            end
        end
    end


    def self.isValidCoin? str
        @base = base2To10 str
        if @base == nil
            return false
        end
        return true
    end

    def self.genNCharStr(n, chr)
        str = ''
        n.times do
            str+=chr
        end
        return str
    end

    def self.get_factor num
        sq = Math.sqrt(num)
        Prime.each do |i|
            if num%i == 0
                return [i]
            end

            puts i

            if i>sq
                break
            end
        end
        return []
    end

    def self.base2To10 str
        base={}
        for i in 2..10
            val = str.to_i(i)
            base["base #{i}"] = [val, get_factor(val)]
            if base["base #{i}"][1].empty?
                return nil
            end
        end
        return base
    end
end

def getValidCoins(n, j)
    ans = CoinJam.genValidCoins(n,j)
end
