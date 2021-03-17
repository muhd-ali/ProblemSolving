/*
Given two sequences, find the length of longest subsequence present in both
of them. A subsequence is a sequence that appears in the same relative order,
but not necessarily contiguous.
For example, “abc”, “abg”, “bdf”, “aeg”, ”acefg”, .. etc are subsequences
of “abcdefg”. So a string of length n has 2^n different possible
subsequences.
*/

class LCS {
  constructor(seq1, seq2) {
    this.memory = {}
    this.seq1 = seq1
    this.seq2 = seq2
  }

  printIndexOf(char, seq, i) {
    if (i === null) {
      console.log('\'' + char + '\' not found in [' + seq + ']')
    } else {
      console.log('\'' + char + '\' in [' + seq + '] at ' + i)
    }
  }

  firstFromLeftIndexOf(seq, char, startingFrom) {
    for (let i = startingFrom; i >= 0; i--) {
      if (char === seq[i]) {
        return i
      }
    }
    return null
  }

  solution_bottomUp(seq1, seq2) {
    const solution = []
    for (let i = 0; i < seq1.length; i++) {
      solution.push([])
      for (let j = 0; j < seq2.length; j++) {
        solution[i].push([])
      }
    }
    for (let i = 0; i < seq1.length; i++) {
      for (let j = 0; j < seq2.length; j++) {
        if (i === 0) {
          if (seq2.includes(seq1[0])) {
            solution[0][j].push(seq1[0])
          }
          continue
        }
        if (j === 0) {
          if (seq1.includes(seq2[0])) {
            solution[i][0].push(seq2[0])
          }
          continue
        }
        const seq2LastChar = seq2[j]
        const p = this.firstFromLeftIndexOf(seq1, seq2LastChar, i)
        const seq1LastChar = seq1[i]
        const q = this.firstFromLeftIndexOf(seq2, seq1LastChar, j)
        const solutions = []
        solutions.push([...solution[i][j-1]])
        solutions.push([...solution[i-1][j]])
        if (p !== null) {
          solutions.push(p > 0 ? [...solution[p-1][j-1], seq2LastChar] : [])
        }
        if (q !== null) {
          solutions.push(q > 0 ? [...solution[i-1][q-1], seq1LastChar] : [])
        }
        // console.log('solutions when i=' + i + ', j=' + j)
        let max_sol = solutions[0]
        for (let sol in solutions) {
          // console.log('=> [' + solutions[sol] + ']')
          if (solutions[sol].length > max_sol.length) {
            max_sol = solutions[sol]
          }
        }
        solution[i][j] = max_sol
      }
    }
    // console.log(solution)
    return solution[seq1.length-1][seq2.length-1]
  }

  solution_topDown(seq1, seq2) {
    return this.solution_topDown_extraParams(
      seq1,
      seq2,
      seq1.length - 1,
      seq2.length - 1
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
    const p = this.firstFromLeftIndexOf(seq1, seq2LastChar, i)
    // this.printIndexOf(seq2LastChar, seq1, p)

    const seq1LastChar = seq1[i]
    const q = this.firstFromLeftIndexOf(seq2, seq1LastChar, j)
    // this.printIndexOf(seq1LastChar, seq2, q)

    const solutions = []
    solutions.push(
      this.solution_topDown_extraParams(seq1, seq2, i, j-1)
    )
    solutions.push(
      this.solution_topDown_extraParams(seq1, seq2, i-1, j)
    )
    if (p !== null) {
      solutions.push(
        [...this.solution_topDown_extraParams(seq1, seq2, p-1, j-1), seq2LastChar]
      )
    }
    if (q !== null) {
      solutions.push(
        [...this.solution_topDown_extraParams(seq1, seq2, i-1, q-1), seq1LastChar]
      )
    }

    // console.log(debug + 'solutions when i=' + i + ', j=' + j)
    let max_sol = solutions[0]
    for (let sol in solutions) {
      // console.log(debug + '=> [' + solutions[sol] + ']')
      if (solutions[sol].length > max_sol.length) {
        max_sol = solutions[sol]
      }
    }
    // console.log(debug + 'max_sol=[' + max_sol + ']')
    this.memory[key] = max_sol
    return max_sol
  }

  solve() {
    // const topDown = this.solution_topDown(
    //   this.seq1,
    //   this.seq2
    // )
    const bottomUp = this.solution_bottomUp(
      this.seq1,
      this.seq2
    )
    return bottomUp
  }
}

function main() {
  let seq1, seq2, solution
  seq1 = 'ATACECGGA'.split('')
  seq2 = 'ATACECGGT'.split('')
  solution = new LCS(seq1, seq2).solve() // should be 'CECGG'
  console.log(solution)

  seq1 = 'ABC'.split('')
  seq2 = 'AEC'.split('')
  solution = new LCS(seq1, seq2).solve() // should be 'ADH'
  console.log(solution)

  seq1 = 'ABCDGH'.split('')
  seq2 = 'AEDFHR'.split('')
  solution = new LCS(seq1, seq2).solve() // should be 'ADH'
  console.log(solution)

  // seq1 = 'AGGTAB'.split('')
  // seq2 = 'GXTXAYB'.split('')
  // solution = new LCS(seq1, seq2).solve() // should be 'GTAB'
  // console.log(solution)
  //
  // seq1 = [14, 57, 32, 8, 17, 27, 20, 18, 1, 36]
  // seq2 = [99, 24, 14, 5, 8, 22, 30, 60, 27, 17]
  // solution = new LCS(seq1, seq2).solve() // should be '[14, 8, 27]'
  // console.log(solution)

}

main()
