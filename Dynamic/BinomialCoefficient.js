/*
1) A binomial coefficient C(n, k) can be defined as the coefficient
of X^k in the expansion of (1 + X)^n.

2) A binomial coefficient C(n, k) also gives the number of ways,
disregarding order, that k objects can be chosen from among n objects;
More formally, the number of k-element subsets (or k-combinations) of
an n-element set.

Write a function that takes two parameters n and k
and returns the value of Binomial Coefficient C(n, k).
For example, your function should return 6 for n = 4 and k = 2
and it should return 10 for n = 5 and k = 2.
*/

class BinomialCoefficient {
  constructor(n, k) {
    this.memory = {}
    this.n = n
    this.k = k
  }

  solution_topDown(n, k) {
    let debug = ''
    for (let i = 0; i < (n-k); i++) {
      debug += ' '
    }
    console.log(debug + 'evaluating for n=' + n + ', k=' + k)
    if (k === n) {
      return 1
    }

  }

  solve() {
    return this.solution_topDown(this.n, this.k)
  }
}

function main() {
  let solution = new BinomialCoefficient(5, 3).solve()
  console.log(solution)

  // solution = new BinomialCoefficient(4, 2).solve() // should be 6
  // console.log(solution)
  //
  // solution = new BinomialCoefficient(5, 2).solve() // should be 10
  // console.log(solution)
}

main()
