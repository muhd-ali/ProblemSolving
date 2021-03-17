class Instant {
  constructor(lineString) {
    const words = lineString.split(' ')
    this.shieldDamageResistance = parseInt(words[0])
    this.program = words[1].split('')
  }

  makeFlip(program) {
    const programCopy = [...program]
    let happened = false
    for (let i = programCopy.length-1; i >= 0; i--) {
      if (i > 0) {
        if (programCopy[i] === 'S' && programCopy[i-1] === 'C') {
          const temp = programCopy[i]
          programCopy[i] = programCopy[i-1]
          programCopy[i-1] = temp
          happened = true
          break
        }
      }
    }
    return {
      'happened': happened,
      'program': programCopy
    }
  }

  calculateDamage(program) {
    let damage = 0
    let strength = 1
    for (let i = 0; i < program.length; i++) {
      if (program[i] === 'C') {
        strength *= 2
      } else if (program[i] === 'S') {
        damage += strength
      }
    }
    return damage
  }

  printDetailsFor(program) {
    const damage = this.calculateDamage(program)
    console.log('program=[' + program + '], damage='+damage);
  }

  solve() {
    let numberOfFlips = 0
    let program = this.program
    // this.printDetailsFor(program)
    let damage = this.calculateDamage(program)
    while (damage > this.shieldDamageResistance) {
      const flip = this.makeFlip(program)
      if (flip.happened) {
        program = flip.program
        damage = this.calculateDamage(program)
        numberOfFlips += 1
      } else {

        return {'possible': false}
      }
    }
    return {
      'possible': true,
      'flips': numberOfFlips
      
    }
  }

  result() {
    const solution = this.solve()
    if (solution.possible) {
      return solution.flips
    }
    return 'IMPOSSIBLE'
  }
}

class Driver {
  constructor() {
  }
  run() {
    let inputString = '';
    let currentLine = 0;
    process.stdin.on('data', inputStdin => {
      inputString += inputStdin;
    })

    let cases
    process.stdin.on('end', _ => {
      cases = inputString.trim().split('\n').map(str => str.trim());
      const numberOfCases = parseInt(cases.shift())
      for (var i = 0; i < numberOfCases; i++) {
        const instant = new Instant(cases[i])
        const result = 'Case #' + (i+1) + ': ' + instant.result()
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
