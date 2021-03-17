class Solver(object):
    def calcLowerLimit(self, numberAsListOfInts):
        return int(''.join([str(int(numberAsListOfInts[0]) - 1)] + ['8']*(len(numberAsListOfInts) - 1)))

    def calcUpperLimit(self, numberAsListOfInts):
        return int(''.join([str(int(numberAsListOfInts[0]) + 1)] + ['0']*(len(numberAsListOfInts) - 1)))

    def calcDiffFromLower(self, numberAsListOfInts):
        result = Solver.convertListOfIntsToNumber(numberAsListOfInts) - self.calcLowerLimit(numberAsListOfInts)
        return result

    def calcDiffFromUpper(self, numberAsListOfInts):
        result = self.calcUpperLimit(numberAsListOfInts) - Solver.convertListOfIntsToNumber(numberAsListOfInts)
        return result

    def solveFor(self, numberAsListOfInts):
        if not numberAsListOfInts:
            return 0

        diffFromLower = self.calcDiffFromLower(numberAsListOfInts)
        diffFromUpper = self.calcDiffFromUpper(numberAsListOfInts)
        if int(numberAsListOfInts[0]) == 9:
            return diffFromLower
        elif int(numberAsListOfInts[0] % 2 != 0): # odd
            return min(diffFromLower, diffFromUpper)

        return self.solveFor(numberAsListOfInts[1:])

    @staticmethod
    def convertListOfIntsToNumber(numberAsListOfInts):
        return int(''.join(map(str, numberAsListOfInts)))

    @staticmethod
    def convertNumberAsStringToListOfInts(numberAsString):
        return list(map(int, list(numberAsString)))

    def solveExample(self):
        result = self.solveFor(
            Solver.convertNumberAsStringToListOfInts('32')
        )
        print(result)

    def solveUsingSTDIN(self):
        import fileinput
        lineNumber = 0
        for line in fileinput.input():
            if lineNumber > 0:
                result = self.solveFor(
                    Solver.convertNumberAsStringToListOfInts(line.strip())
                )
                print("Case #{}: {}".format(lineNumber, result))
            lineNumber += 1

if __name__ == "__main__":
    Solver().solveUsingSTDIN()
