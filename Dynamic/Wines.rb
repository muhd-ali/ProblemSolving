# Question:

# Imagine you have a collection of N wines placed next to each other on a shelf.
# For simplicity, let's number the wines from left to right as they are standing on the shelf with integers from 1 to N, respectively.
# The price of the ith wine is pi. (prices of different wines can be different).
# Because the wines get better every year, supposing today is the year 1, on year y the price of the ith wine will be y*pi,
# i.e. y-times the value that current year.
# You want to sell all the wines you have, but you want to sell exactly one wine per year, starting on this year.
# One more constraint - on each year you are allowed to sell only either the leftmost or the rightmost wine on the shelf and you are not allowed to reorder the wines on the shelf
# (i.e. they must stay in the same order as they are in the beginning).
# You want to find out, what is the maximum profit you can get, if you sell the wines in optimal order?


# Solution:

class Pair
  attr_accessor :cost, :year

  def initialize(cost, year)
    @cost = cost
    @year = year
  end
end

# Recursive:
def maxProfitHelper(wineCosts, year)
  if (wineCosts.length == 1)
    return [Pair.new(wineCosts[0], year)]
  end

  [
    maxProfitHelper(wineCosts[1 .. -1], year + 1) << Pair.new(wineCosts[0], year),
    maxProfitHelper(wineCosts[0 .. -2], year + 1) << Pair.new(wineCosts[-1], year)
  ].max{ |a, b|
    a.inject(0){ |sum, x| sum + (x.cost * x.year) } <=> b.inject(0){ |sum, x| sum + (x.cost * x.year) }
  }
end

def calcSum arr
  return arr.inject(0) { |sum, x|
    sum + (x.cost * x.year)
  }
end

def maxProfitRec wineCosts
  sol = maxProfitHelper(wineCosts, 1)
  # print "#{sol}\n"
  return calcSum sol
end

# costs = [1, 4, 2, 3]
# costs = [2, 3, 5, 1, 4]
costs = *(1..5)
print "#{maxProfitRec(costs)}\n"
