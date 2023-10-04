package binarytrees;

import java.util.*;

public class BinaryTreeProblems {

    /**
     * LC 437. Path Sum III
     * @param root
     * @param targetSum
     * @return
     */
    Map<Long, Integer> prefixSum;
    Integer res;
    public int pathSum(TreeNode root, int targetSum) {
        prefixSum = new HashMap<>();
        res = 0;
        dfsPathSum(root, 0L, targetSum);
        return res;
    }

    private void dfsPathSum(TreeNode node, Long prev, int target) {
        if (node == null) return;
        Long prefix = node.val + prev;

        // Update result if needed
        res += prefixSum.getOrDefault(prefix-target, 0);
        if (prefix == target) res ++;

        prefixSum.put(prefix, prefixSum.getOrDefault(prefix, 0) + 1);

        dfsPathSum(node.left, prefix, target);
        dfsPathSum(node.right, prefix, target);

        prefixSum.put(prefix, prefixSum.get(prefix)-1);
    }

    /**
     * LC 1448. Count Good Nodes in Binary Tree
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;

        List<TreeNode> goodNodes = new ArrayList<>();
        dfsGoodNodes(root, root.val, goodNodes);

        return goodNodes.size();
    }

    private void dfsGoodNodes(TreeNode node, int value, List<TreeNode> goodNodes) {
        if (value <= node.val) {
            goodNodes.add(node);
            value = node.val;
        }

        if (node.left != null) dfsGoodNodes(node.left, value, goodNodes);
        if (node.right != null) dfsGoodNodes(node.right, value, goodNodes);
    }

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
