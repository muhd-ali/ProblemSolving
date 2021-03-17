def valueAtIndexAfterKRotations(a:list, k:int, index):
    if (index >= k):
        return a[index - k]
    else:
        return a[len(a) - k + index]


def circularArrayRotation(a, k, queries):
    result = map(lambda x: valueAtIndexAfterKRotations(a, k % len(a), x), queries)
    return list(result)



if __name__ == "__main__":
    a = [3, 4, 5]
    k = 2
    queries = [1, 2]
    result = circularArrayRotation(a, k, queries)
    print(result)

    a = [1, 2, 3]
    k = 2
    queries = [0, 1, 2]
    result = circularArrayRotation(a, k, queries)
    print(result)