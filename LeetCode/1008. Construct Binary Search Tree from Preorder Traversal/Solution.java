// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        if (preorder.length > 0) {
            root = new TreeNode(preorder[0]);
            for (int i = 1; i < preorder.length; i++) {
                insertElt(root, preorder[i]);
            }
        }
        return root;
    }

    void insertElt(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertElt(root.right, val);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertElt(root.left, val);
            }
        }
    }
}