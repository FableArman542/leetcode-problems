package binarytrees;

import java.util.*;

public class BinaryTreeProblems {

    /**
     * LC 1161. Maximum Level Sum of a Binary Tree
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {

        int level = 1, currentLevel = 1;
        int maxSum = root.val;
        TreeNode pointer = root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);

            if (node == pointer && queue.peekLast() != null) {
                // choose a new pointer
                pointer = queue.peekLast();
                ++ currentLevel;
                // check if level is replaced
                int s = queue.stream().mapToInt(b -> b.val).sum();
                if (s > maxSum) {
                    maxSum = s;
                    level = currentLevel;
                }
            }
        }

        return level;
    }

    /**
     * LC 199. Binary Tree Right Side View
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode pointer = root;
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
            if (pointer == node) {
                res.add(pointer.val);
                pointer = queue.peekLast();
            }
        }

        return res;
    }

    /**
     * LC 101. Symmetric Tree
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return dfsSymetric(root.left, root.right);
    }

    private boolean dfsSymetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val
                && dfsSymetric(left.left, right.right)
                && dfsSymetric(left.right, right.left);
    }

    /**
     * LC 700. Search in a Binary Search Tree
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val > root.val) return searchBST(root.right, val);
        else if (val < root.val) return searchBST(root.left, val);
        return root;
    }

    /**
     * LC 98. Validate Binary Search Tree
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min,  long max) {
        return  (root.val > min && root.val < max)
                && root.left == null  || isValidBST(root.left, min, root.val)
                && root.right == null || isValidBST(root.right, root.val, max);
    }

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
