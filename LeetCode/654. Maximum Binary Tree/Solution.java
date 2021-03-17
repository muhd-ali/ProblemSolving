class Solution {
  public static void main(String[] args) {
    int[] nums = new int[] {3, 2, 1, 6, 0, 5};
    var obj = new Solution();
    TreeNode t = obj.constructMaximumBinaryTree(nums);
    System.out.println(t);
    // System.out.println(TreeNode.example());
    // TreeNode.example
  }

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return this.constructMaximumBinaryTree(nums, 0, nums.length-1);
  }

  public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    int maxInd = start;
    for (int i=start+1; i<=end; i++) {
      if (nums[i] > nums[maxInd]) {
        maxInd = i;
      }
    }
    TreeNode node = new TreeNode(nums[maxInd]);
    node.left = this.constructMaximumBinaryTree(nums, start, maxInd-1);
    node.right = this.constructMaximumBinaryTree(nums, maxInd+1, end);
    return node;
  }
}
