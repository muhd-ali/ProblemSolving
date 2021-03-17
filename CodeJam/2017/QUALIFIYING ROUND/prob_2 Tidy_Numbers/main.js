const fs = require('fs')

class TidyNumbers {
  constructor(filenameWithoutExtension) {
    this.filenameWithoutExtension = filenameWithoutExtension
    this.readInputFile = this.makePromiseToReadInputFrom(filenameWithoutExtension+'.in')
  }

  makePromiseToReadInputFrom(filename) {
    return new Promise((resolve, reject) => {
      fs.readFile(filename, 'utf8', (err, data) => {
        if (!err) {
          resolve(data)
        } else {
          reject(err)
        }
      })
    })
  }

  run() {
    this.readInputFile.then((data) => {
      const lines = data.split('\n')
      const numberOfCases = parseInt(lines.shift())
      const numberOfLines = lines.length - 1
      const count = Math.min(numberOfLines, numberOfCases)
      const stream = fs.createWriteStream(this.filenameWithoutExtension+'.out')
      stream.once('open', (_) => {
        for (let i=0; i<count; i++) {
          const line = lines[i]
          const solution = this.findTidyNumber(line)
          const str = 'Case #' + (i+1) + ': ' + solution + '\n'
          stream.write(str)
        }
        stream.end()
      })
    }).catch((err) => {
      console.log(err)
    })
  }

  fix(number, index) {
    const prevIndex = index - 1
    if (prevIndex < 0) {
      return number
    }

    if (number[index] < number[prevIndex]) {
      number[prevIndex]--
      for (let i=index; i<number.length; i++)
        number[i] = 9
    }

    return this.fix(number, index-1)
  }

  cleanNumber(number) {
    while (number[0] == 0) {
      number.shift()
    }
    return number.join('')
  }

  findTidyNumber(lessThan) {
    let number = lessThan.split('').map(digit => parseInt(digit))
    this.fix(number, number.length - 1)
    return this.cleanNumber(number)
  }
}

function main() {
  const args = process.argv
  let filenameWithoutExtension = args[2]
  new TidyNumbers(filenameWithoutExtension).run()
}

main()
