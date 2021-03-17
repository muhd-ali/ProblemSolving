function formingMagicSquare(s) {
  let sum = [...Array(Math.pow(s.length, 2)).keys()]
  .map(x => x+1)
  .reduce(
    (e1, e2) => e1+e2
  )/s.length
  let sums = {};
  sums.row = s.map(
    r => r.reduce(
      (e1, e2) => e1 + e2
    )
  )
  sums.col = s.reduce(
    (r1, r2) => r1.map(
      (e1, i1) => e1 + r2[i1]
    )
  )
  new_mat = Object.assign([], s)
  for (let i=0; i<s.length; i++) {
    let min_col = null;
    for (let j=0; j<s.length; j++) {
      if (sums.row[i] === sums.col[j]) {
        min_col = j
        break
      }
    }
    new_mat[i][min_col] = sum - sums.row[i] + s[i][min_col]
  }
}

let input = [
  [5, 3, 4],
  [1, 5, 8],
  [6, 4, 2],
]

formingMagicSquare(input)
