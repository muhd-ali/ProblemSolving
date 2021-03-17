class Instant {
  constructor(value_A) {
    this.requiredPreparedArea = value_A
    this.field = this.generateField()
    this.startingCell = [1, 1]
    this.center = [...this.startingCell]
    this.direction = 'up'
  }

  gridString() {
    let str = ''
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        let char
        if (i === this.center[0] && j === this.center[1]) {
          char = '*'
        } else if (this.field[i][j]) {
          char = '1'
        } else {
          char = '0'
        }
        str += char + ','
      }
      str += '\n'
    }
    str += '\n'
    return str
  }

  generateField() {
    const field = []
    for (let i = 0; i < 1000; i++) {
      field.push([])
      for (let j = 0; j < 1000; j++) {
        field[i].push(false)
      }
    }
    return field
  }

  nextTarget() {
    return this.center
  }

  updateCenterIfNeeded() {
    let key = ''
    let blockPrepared = true
    for (let i=-1; i<2; i++) {
      for (let j=-1; j<2; j++) {
        if (!this.field[this.center[0]+i][this.center[1]+j]) {
          blockPrepared = false
        }
      }
    }

    if (blockPrepared) {
      if (this.direction === 'up') {
        this.center[0] += 1
      }
    }
  }

  gopherPrepared(coord) {
    this.field[coord[0]-1][coord[1]-1] = true
    this.updateCenterIfNeeded()
  }

  leaveGopherOn() {
    let coord = this.nextTarget()
    coord = coord.map(x => x+1).join(' ')
    return coord
  }
}

class Driver {
  run() {
    let readline = require('readline')
    let rl = readline.createInterface(process.stdin, process.stdout)
    let readMode = 'firstLine'
    let numberOfCases
    let currentCaseNumber = 1
    let value_A = -1
    let solver = null
    let nextTarget
    const fs = require('fs')
    // const stream = fs.createWriteStream('log.txt')
    // stream.once('open', fd => {
      rl.on('line', function(line) {
        if (readMode === 'firstLine') {
          readMode = 'startCase'
          numberOfCases = parseInt(line)
        } else if (readMode === 'startCase') {
          value_A = parseInt(line)
          solver = new Instant(value_A)
          currentCaseNumber += 1
          readMode = 'interaction'
          nextTarget = solver.leaveGopherOn()
          console.log(nextTarget)
        } else if (readMode === 'interaction') {
          const coord = line.split(' ').map(x => parseInt(x))
          if (coord[0] === 0 && coord[1] === 0) {
            readMode = 'startCase'
            if (currentCaseNumber > numberOfCases) {
              process.exit(0)
            }
            // stream.write('case solved\n')
          } else if (coord[0] === -1 && coord[1] === -1) {
            readMode = 'startCase'
            process.exit(0)
          } else {
            solver.gopherPrepared(coord)
            nextTarget = solver.leaveGopherOn()
            // stream.write(solver.gridString())
            console.log(nextTarget)
          }
        }
      }).on('close',function(){
        process.exit(0)
      })
    // })
  }
}

function main() {
  const driver = new Driver()
  driver.run()
}

main()
