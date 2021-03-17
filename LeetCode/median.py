import math

class Solution(object):
    def binarySearch(self, n, array):
        print(
        "running binary search with arg:\n" +
        'n = ' + str(n) + '; array = ' + str(array)
        )

        array_from = 0; array_to = len(array)

        if n < array[array_from]:
            return (False, (-math.inf, array_from))
        elif n > array[array_to - 1]:
            return (False, (array_to, math.inf))


        while array_to - array_from > 0:
            midValIndex = array_from + int((array_to - array_from) / 2) - 1
            midVal = array[midValIndex]
            if midVal == n:
                return (True, (midValIndex, midValIndex))
            elif midVal < n:
                array_from = midValIndex
            elif midVal > n:
                array_to = midValIndex

            if array_to - array_from == 1:
                for i in range(array_from, array_to):
                    if array[i] == n:
                        return (True, (i, i))
                return (False, (array_from, array_to))

        return (False, (array_from, array_to))

    def initializeVars(self, nums1, nums2):
        array1 = nums1; array2 = nums2
        array1_from = 0; array1_to = len(array1)
        array2_from = 0; array2_to = len(array2)
        return (array1, array1_from, array1_to, array2, array2_from, array2_to)

    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        (array1, array1_from, array1_to, array2, _, _) = self.initializeVars(nums1, nums2)
        k = 0
        while array1_to - array1_from > 0:
            k += 1
            if k > 10:
                break
            midValIndex = array1_from + int((array1_to - array1_from) / 2) - 1
            midVal = array1[midValIndex]
            (foundInNums2, (array2_from, array2_to)) = self.binarySearch(midVal, array2)
            print(
                'array1_from = ' + str(array1_from) + '; ' +
                'array1_to = ' + str(array1_to) + '; ' +
                'array2_from = ' + str(array2_from) + '; ' +
                'array2_to = ' + str(array2_to) + '; ' + "\n"
            )
            if array2_from > len(array2) - 1:
                (array1, array1_from, array1_to, array2, _, _) = self.initializeVars(array2, array1)
            elif array2_from > len(array2) - 1:
                (array1, array1_from, array1_to, array2, _, _) = self.initializeVars(array2, array1)
            print((foundInNums2, (array2_from, array2_to)))





def main():
    sol = Solution().findMedianSortedArrays([1, 2], [3, 4])
    # sol = Solution().binarySearch(20, [1, 2, 5, 6, 8, 9, 11,  15,  17, 20, 21, 23, 25, 26, 30])

    print(sol)

main()
