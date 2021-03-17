public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public String toString() {
      return this.toString("");
    }

    public String toString(String indent) {
      String thisString = indent + this.val;
      String leftString = this.left == null ? "" : this.left.toString(indent+"\t");
      String rightString = this.right == null ? "" : this.right.toString(indent+"\t");
      return thisString + "\nL:" + leftString + "\nR:" + rightString;
    }

    static TreeNode example() {
      TreeNode t = new TreeNode(10);
      t.left = new TreeNode(5);
      t.left.left = new TreeNode(2);
      t.left.right = new TreeNode(7);
      t.right = new TreeNode(20);
      return t;
    }
}
