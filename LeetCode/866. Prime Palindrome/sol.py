class Solution:
    def primePalindrome(self, N):
        """
        :type N: int
        :rtype: int
        """
        num = N
        limit = 2 * 100000000
        while num < limit:
            num = Solution.greaterThanEqualToPalindrome(num)
            print(num)
            if Solution.isPrime(num):
                return num
            num += 1
        return -1

    @staticmethod
    def greaterThanEqualToPalindrome(num):
        arr = list(map(int, list(str(num))))
        res = Solution.greaterThanEqualToPalindrome_help(arr)
        return int(''.join(map(str, res)))


    @staticmethod
    def greaterThanEqualToPalindrome_help(arr):
        print(arr)
        if len(arr) < 2:
            return arr

        if arr[0] < arr[-1]:
            for i in range(len(arr)-2, -1, -1):
                if arr[i] < 9:
                    arr[i] += 1
                    for j in range(i+1, len(arr) - 1):
                        arr[j] = 0
                    break
            arr[-1] = arr[0]
        return [arr[0]] + Solution.greaterThanEqualToPalindrome_help(arr[1:-1]) + [arr[0]]


    @staticmethod
    def isPrime(n):
        if n == 2 or n == 3: return True
        if n < 2 or n%2 == 0: return False
        if n < 9: return True
        if n%3 == 0: return False
        r = int(n**0.5)
        f = 5
        while f <= r:
            if n%f == 0: return False
            if n%(f+2) == 0: return False
            f +=6
        return True

def main():
    sol = Solution()
    num = 13
    res = sol.greaterThanEqualToPalindrome(num)
    print(res)

main()
