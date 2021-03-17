class Solution:
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        twoSumCache = {}

        triplets = set()
        for i in range(0, len(nums)):
            if i in twoSumCache:
                pairs = twoSumCache[i]
            else:
                pairs = Solution.twoSumN(nums, -nums[i], i)
            for p in pairs:
                triplet = tuple(sorted([nums[i], p[0], p[1]]))
                triplets.add(triplet)
        return list(triplets)

    @staticmethod
    def twoSumN(nums, n, currentIndex):
        numCount = {}
        for i in range(0, len(nums)):
            if i == currentIndex:
                continue
            if nums[i] in numCount:
                numCount[nums[i]] += 1
            else:
                numCount[nums[i]] = 1

        pairs = set()
        for i in range(0, len(nums)):
            if i == currentIndex:
                continue
            pair = n - nums[i]
            if pair == nums[i] and numCount[nums[i]] < 2:
                continue
            if pair in numCount and numCount[nums[i]] > 0 and numCount[pair] > 0:
                pairs.add((nums[i], pair))
                numCount[nums[i]] -= 1
                numCount[pair] -= 1
        return pairs

def main():
    sol = Solution()
    import random
    n = 1000
    nums = list(range(-n, n))
    random.shuffle(nums)
    res = sol.threeSum(nums)
    # print(res)

main()
