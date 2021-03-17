// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else {
            insertIntoBST_helper(root, val);
            return root;
        }
    }

    void insertIntoBST_helper(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBST_helper(root.right, val);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBST_helper(root.left, val);
            }
        }
    }
}