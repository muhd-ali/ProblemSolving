from collections import deque

class Tree:
    def __init__(self, val=None):
        self.left = None
        self.right = None
        self.val = None
        if val is not None:
            self.val = val

    def createArr(self):
        queue = deque([self])
        result = []
        while queue:
            node = queue.popleft()
            if node is None:
                result.append(None)
                continue
            result.append(node.val)
            queue.append(node.left)
            queue.append(node.right)
        while result[-1] is None:
            del result[-1]
        return result


    @staticmethod
    def example():
        root = Tree(val=1)
        root.left = Tree(val=2)
        root.left.left = Tree(val=4)
        root.left.right = Tree(val=5)
        root.right = Tree(val=3)
        root.right.left = Tree(val=6)
        root.right.right = Tree(val=7)
        return root

    def textValue(self):
        return str(self.sum)

    def __str__(self):
        space = ' ----- :'
        return self.str_help(space)

    def str_help(self, space):
        text = self.textValue()
        if self.left:
            text += "\n" + space + '|L|' + self.left.str_help(space + space)
        if self.right:
            text += "\n" + space + '|R|' + self.right.str_help(space + space)
        return text

    @staticmethod
    def createFromArr(arr):
        if not arr:
            return None
        val = arr.pop(0)
        if val is None:
            return None
        root = Tree(val=val)
        if not root.val:
            raise 'Error'
        queue = deque([root])
        while queue:
            node = queue.popleft()
            if len(arr) < 1:
                return root
            left = arr.pop(0)
            if left is not None:
                node.left = Tree(val=left)
                queue.append(node.left)
            if len(arr) < 1:
                return root
            right = arr.pop(0)
            if right is not None:
                node.right = Tree(val=right)
                queue.append(node.right)
        return root

class Solution:
    def pruneTree(self, tree):
        self.calcInnerSum(tree)
        self.dropZeroSumNodes(tree)
        return tree

    @staticmethod
    def calcInnerSum(tree):
        if tree is None:
            return 0
        tree.sum = tree.val
        leftSum = Solution.calcInnerSum(tree.left)
        rightSum = Solution.calcInnerSum(tree.right)
        tree.sum += leftSum + rightSum
        return tree.sum

    @staticmethod
    def dropZeroSumNodes(tree):
        if tree is None:
            return
        if tree.left and tree.left.sum == 0:
            tree.left = None
        Solution.dropZeroSumNodes(tree.left)

        if tree.right and tree.right.sum == 0:
            tree.right = None
        Solution.dropZeroSumNodes(tree.right)



def main():
    sol = Solution()
    nums = [1,0,1,0,0,0,1]
    tree = Tree.createFromArr(nums)
    res = sol.pruneTree(tree)
    print(res)

main()
