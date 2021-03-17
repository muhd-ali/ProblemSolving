/*
Given a string, print the longest repeating subsequence such that
the two subsequence don’t have same string character at same position,
i.e., any i’th character in the two subsequences shouldn’t have the
same index in the original string.
*/

class LRS {
  constructor(seq) {
    this.memory = {}
    this.seq = seq
  }

  firstFromLeftIndexOf(seq, char, startingFrom) {
    for (let i = startingFrom; i >= 0; i--) {
      if (char === seq[i]) {
        return i
      }
    }
    return null
  }

  solution_bottomUp(seq) {
  }

  solution_topDown(seq) {
    return this.solution_topDown_extraParams(
      seq,
      seq,
      seq.length - 1,
      seq.length - 1
    )
  }

  solution_topDown_extraParams(seq1, seq2, i, j) {
    if (i < 0 || j < 0) {
      return []
    }

    // let debug = ''
    // for (let i = 0; i < (seq1.length + seq2.length - i - j); i++) {
    //   debug += '  '
    // }

    const key = (i + ',' + j)
    if (key in this.memory) {
      return this.memory[key]
    }
    const seq2LastChar = seq2[j]
    let startingFrom = i==j ? i-1 : i
    const p = this.firstFromLeftIndexOf(seq1, seq2LastChar, startingFrom)
    // this.printIndexOf(seq2LastChar, seq1, p)

    const seq1LastChar = seq1[i]
    startingFrom = i==j ? j-1 : j
    const q = this.firstFromLeftIndexOf(seq2, seq1LastChar, startingFrom)
    // this.printIndexOf(seq1LastChar, seq2, q)

    const solutions = []
    solutions.push(
      [this.solution_topDown_extraParams(seq1, seq2, i, j-1), 0]
    )
    solutions.push(
      [this.solution_topDown_extraParams(seq1, seq2, i-1, j), 1]
    )
    if (p !== null && p !== j) {
      solutions.push(
        [[...this.solution_topDown_extraParams(seq1, seq2, p-1, j-1), seq2LastChar], 2]
      )
    }
    if (q !== null && q !== i) {
      solutions.push(
        [[...this.solution_topDown_extraParams(seq1, seq2, i-1, q-1), seq1LastChar], 3]
      )
    }

    // console.log(debug + 'solutions when i=' + i + ', j=' + j)
    let max_sol = solutions[0][0]
    for (let sol in solutions) {
      // console.log(debug + '=> [' + solutions[sol] + ']')
      if (solutions[sol][0].length > max_sol.length) {
        max_sol = solutions[sol][0]
      }
    }
    // console.log(debug + 'max_sol=[' + max_sol + ']')
    this.memory[key] = max_sol

    // console.log(debug + 'solution for i=' + i + ', j=' + j + ', p=' + p + ', q=' + q +' = [' + max_sol + ']')
    return max_sol
  }

  solve() {
    const topDown = this.solution_topDown(this.seq)
    const bottomUp = this.solution_bottomUp(this.seq)
    return topDown
  }
}

function main() {
  let seq, solution
  seq = []
  seq = 'ABEBD'.split('')
  solution = new LRS(seq).solve() // should be 'ABD'
  console.log(solution)

  seq = 'AABEBCDD'.split('')
  solution = new LRS(seq).solve() // should be 'ABD'
  console.log(solution)

  seq = 'aab'.split('')
  solution = new LRS(seq).solve() // should be 'a'
  console.log(solution)

  seq = 'ATACTCGGA'.split('')
  solution = new LRS(seq).solve() // should be 'ATCG'
  console.log(solution)

  seq = 'AABEBCDD'.split('')
  solution = new LRS(seq).solve() // should be 'ABD'
  console.log(solution)
}

main()
