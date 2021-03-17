def binarySearchNearest(data, val):
    highIndex = len(data)-1
    lowIndex = 0
    while highIndex > lowIndex:
        index = int((highIndex + lowIndex) / 2)
        sub = data[index]
        if data[lowIndex] == val:
            return [lowIndex, lowIndex]
        elif sub == val:
            return [index, index]
        elif data[highIndex] == val:
            return [highIndex, highIndex]
        elif sub > val:
            if highIndex == index:
                return sorted([highIndex, lowIndex])
            highIndex = index
        else:
            if lowIndex == index:
                return sorted([highIndex, lowIndex])
            lowIndex = index
    return sorted([highIndex, lowIndex])


def nearestForHouse(sortedStores, house):
    nearest = binarySearchNearest(sortedStores, house)
    difs = [abs(house - sortedStores[i]) for i in nearest]
    return sortedStores[nearest[0]] if difs[0] < difs[1] else sortedStores[nearest[1]]


def solution(stores, houses):
    nearest = []
    sortedStores = sorted(stores)
    for h in houses:
        nearest.append(nearestForHouse(sortedStores, h))
    return nearest


stores = [1, 5, 10, 15, 20]
houses = [0, 4, 9, 14, 19]
sol = solution(stores, houses)
print(sol)
