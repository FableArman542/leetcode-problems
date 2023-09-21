package binarytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryTreeProblems {

    /**
     * LC 226. Invert Binary Tree
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        if (root.left != null) root.left = invertTree(root.left);
        if (root.right != null) root.right =  invertTree(root.right);
        return root;
    }

    /**
     * LC 872. Check if two trees are leaf-similar
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return Objects.equals(
                dfs(root1),
                dfs(root2)
        );
    }

    private List<Integer> dfs(TreeNode root) {
        if (root.left == null && root.right == null) return List.of(root.val);
        List<Integer> l = new ArrayList<>();
        if (root.left != null) {
            l.addAll(dfs(root.left));
        }

        if (root.right != null) {
            l.addAll(dfs(root.right));
        }
        return l;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
