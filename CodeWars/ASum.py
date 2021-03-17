import math

def cubeSeriesSum(n):
    return int(0.25 * math.pow(n, 2) * math.pow(n + 1, 2))

def findNb(m):
    n = (-1 + math.sqrt(8 * math.sqrt(m) + 1)) / 2
    if (cubeSeriesSum(n) == m):
        return int(n)
    else:
        return -1


if __name__ == "__main__":
    inputVal = 3153
    output = findNb(inputVal)
    print(output)