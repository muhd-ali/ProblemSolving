class Instant {
  constructor(line1, line2) {
    this.length = parseInt(line1)
    this.list = line2.split(' ').map(x => parseInt(x))
  }

  check(list) {
    let listEven = []
    let listOdd = []
    list.forEach((val, i) => {
      if (i%2 === 0) {
        listEven.push(val)
      } else {
        listOdd.push(val)
      }
    })
    listEven.sort((a, b) => a-b)
    listOdd.sort((a, b) => a-b)
    
    // console.log(listEven);
    // console.log(listOdd);
    
    for (let i = 0; i < Math.min(listEven.length, listOdd.length); i++) {
      // console.log('problem between');
      if (listEven[i] > listOdd[i]) {
        // console.log(2*i, 2*i+1);        
        return 2*i;
      }
      if (i+1 < listEven.length && listOdd[i] > listEven[i+1]) {
        // console.log(2*i + 1, 2*i + 2);        
        // console.log(listOdd[i], listEven[i+1]);        
        return 2*i + 1;
      }
  }
    return -1
  }

  solve() {
    const result = this.check(this.list)
    // console.log(this.list);
    return result
  }

  result() {
    const solution = this.solve()
    if (solution >= 0) {
      return solution
    }
    return 'OK'
  }
}

class Driver {
  run() {
    let inputString = '';
    let currentLine = 0;
    process.stdin.on('data', inputStdin => {
      inputString += inputStdin;
    })

    let lines
    process.stdin.on('end', _ => {
      lines = inputString.trim().split('\n').map(str => str.trim());
      const numberOfCases = parseInt(lines.shift())
      for (var i = 0; i < 2*numberOfCases; i+=2) {
        const instant = new Instant(lines[i], lines[i+1])
        const result = 'Case #' + ((i/2)+1) + ': ' + instant.result()
        console.log(result)
      }
    })
  }
}

function main() {
  const driver = new Driver()
  driver.run()
}

main()
