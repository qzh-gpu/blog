package huawei;

/*
 * 【原理说明 - 递归后序遍历】
 * 1. 最大路径和可能出现在三种位置：
 *    a. 完全在左子树中。
 *    b. 完全在右子树中。
 *    c. 经过当前根节点，即左子树贡献一部分 + 当前节点 + 右子树贡献一部分。
 * 2. 对于每个节点，我们需要知道以该节点为起点向下的最大单边路径和（即只能向左或向右走，不能左右都走）。
 *    这个单边和 = node.val + max(0, 左子树的单边贡献, 右子树的单边贡献)。
 *    如果左右子树贡献为负数，则取 0（相当于不选那一侧）。
 * 3. 同时，在计算单边和的过程中，用当前节点的单边和 + 左子树贡献 + 右子树贡献（即经过当前节点的完整路径和）来更新全局最大路径和。
 * 4. 递归函数返回的是当前节点向下的最大单边贡献，以便父节点使用。
 * 5. 全局变量 maxSum 记录所有经过节点的情况中的最大值。
 * 6. 时间复杂度 O(n)，空间复杂度 O(h)（递归调用栈深度为树高）。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 全局变量，记录最大路径和
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 调用递归函数，开始计算
        maxGain(root);
        return maxSum;
    }

    /**
     * 计算以当前节点为起点的最大单边贡献（即只能向左右走一条路径）
     * 同时更新全局最大路径和
     */
    private int maxGain(TreeNode node) {
        // 1. 如果节点为空，贡献为 0
        if (node == null) {
            return 0;
        }

        // 2. 递归计算左子树和右子树的最大单边贡献
        //    如果子树贡献为负数，我们取 0，即不选该侧（相当于剪掉负贡献）
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // 3. 经过当前节点的完整路径和 = 左贡献 + 当前节点值 + 右贡献
        int currentPathSum = leftGain + node.val + rightGain;

        // 4. 更新全局最大路径和
        maxSum = Math.max(maxSum, currentPathSum);

        // 5. 返回当前节点向上的最大单边贡献（只能选择左或右中较大的一侧）
        return node.val + Math.max(leftGain, rightGain);
    }
}