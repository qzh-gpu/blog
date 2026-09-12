---
title: "236. 二叉树的最近公共祖先（二叉树 / 递归 / DFS）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 二叉树, 递归, DFS, 中等]
category: codetop
difficulty: 中等
leetcodeId: 236
excerpt: "递归后序遍历寻找二叉树中两个节点的最近公共祖先。"
---

# 236. 二叉树的最近公共祖先（二叉树 / 递归 / DFS）

## 题目

给定一个二叉树，找到该树中两个指定节点的最近公共祖先。  
最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

## 示例

输入：`root = [3,5,1,6,2,0,8,null,null,7,4]`, `p = 5`, `q = 1`  
输出：`3`  
解释：节点 5 和节点 1 的最近公共祖先是节点 3。

输入：`root = [3,5,1,6,2,0,8,null,null,7,4]`, `p = 5`, `q = 4`  
输出：`5`  
解释：节点 5 和节点 4 的最近公共祖先是节点 5，因为根据定义最近公共祖先节点可以为节点本身。

## 解题思路

采用**递归后序遍历（DFS）**。  
对于当前节点 `root`：

- 如果 `root` 为空，或者 `root` 就是 `p` 或 `q`，直接返回 `root`。
- 递归在左子树中查找 `p` 和 `q` 的最近公共祖先，结果记为 `left`。
- 递归在右子树中查找，结果记为 `right`。
- 如果 `left` 和 `right` 都非空，说明 `p` 和 `q` 分别位于左右子树中，当前 `root` 就是最近公共祖先，返回 `root`。
- 如果只有 `left` 非空，说明 `p` 和 `q` 都在左子树中，返回 `left`。
- 否则返回 `right`。

时间复杂度 `O(n)`，空间复杂度 `O(h)`（递归栈深度，h 为树高）。

## 解答

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件：如果当前节点为空，或者当前节点就是 p 或 q，直接返回当前节点
        // 为什么返回 root？因为如果 root 是 p 或 q，它可能就是最近公共祖先（一个节点可以是自己的祖先）
        if (root == null || root == p || root == q) {
            return root;
        }

        // 递归在左子树中查找 p 和 q 的最近公共祖先
        // 使用 TreeNode 类型接收结果，因为返回的是节点对象
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // 递归在右子树中查找 p 和 q 的最近公共祖先
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都找到了结果（即 left 和 right 都不为空）
        // 说明 p 和 q 分别位于当前节点的左右两侧，当前节点就是最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 如果只有左子树找到结果，说明 p 和 q 都在左子树中，返回左子树的结果
        // 如果只有右子树找到结果，说明 p 和 q 都在右子树中，返回右子树的结果
        // 如果左右都为空，说明当前子树中不包含 p 和 q，返回 null（由上一层处理）
        return left != null ? left : right;
    }
}
```
