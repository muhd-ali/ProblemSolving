/*
You are working at the cash counter at a fun-fair
and you have different types of coins available to you
in infinite quantities. The value of each coin is already given.
Can you determine the number of ways of making change
for a particular number of units using the given types of coins?
*/

class CoinChange {
  constructor(number, coins) {
    this.memory = {}
    this.number = number
    this.coins = coins.sort()
  }

  solution_bottomUp(number, coins) {
  }

  solution_topDown(number, coins) {
    if (number === 0 || number === coins[0]) {
      return 1
    }

    if (number < coins[0]) {
      return 0
    }

    if (number in this.memory) {
      return this.memory[number]
    }

    console.log('called for ' + number)

    let ways = 0
    for (let i=0; i<coins.length; i++) {
      const remaining = number - coins[i]
      const innerWays = this.solution_topDown(remaining, coins)
      if (innerWays > 0) {
        console.log(coins[i] + '+' + remaining + '=' + number)
      }
      ways += innerWays
    }
    console.log('ways(' + number + ')=' + ways)
    this.memory[number] = ways
    return ways
  }

  solution_topDown_extraParams(number, coins) {
  }

  solve() {
    const topDown = this.solution_topDown(
      this.number,
      this.coins
    )
    // const bottomUp = this.solution_bottomUp(
    //   this.number,
    //   this.coins
    // )
    return topDown
  }
}

function main() {
  let number, coins, solution
  number = 4
  coins = [1,2]
  solution = new CoinChange(number, coins).solve() // should be 2
  console.log(solution)

  // number = 100
  // coins = [1,3,5]
  // solution = new CoinChange(number, coins).solve() // should be 2
  // console.log(solution)

  // number = 3
  // coins = [8,3,1,2]
  // solution = new CoinChange(number, coins).solve() // should be 3
  // console.log(solution)

  // number = 10
  // coins = [2,5,3,6]
  // solution = new CoinChange(number, coins).solve() // should be 5
  // console.log(solution)

  // number = 3
  // coins = [1,2,3]
  // solution = new CoinChange(number, coins).solve() // should be 4
  // console.log(solution)
}

main()


/*
  when coins are [1,2]
  For 3:
    ways(2) + ways(1)
    ways(2)=2: [1,1], [2]
    ways(1)=1: [1]
    should be 2: [1,1,1], [1,2]
    computes to be 3 ([1,2] counted twice)

  For 4:
    ways(3) + ways(2)
    ways(3)=2: [1,1,1], [1,2]
    ways(2)=2: [1,1], [2]
    should be 3: [1,1,1,1], [1,1,2], [2,2]
    computes to be 5
*/
