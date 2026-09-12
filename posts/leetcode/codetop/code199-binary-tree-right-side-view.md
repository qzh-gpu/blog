---
title: "199. 二叉树的右视图（二叉树 / BFS）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 二叉树, BFS, 中等]
category: codetop
difficulty: 中等
leetcodeId: 199
excerpt: "BFS 按层遍历，记录每层最后一个节点作为右视图。"
---

# 199. 二叉树的右视图（二叉树 / BFS）

## 题目

给定一个二叉树的根节点 `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

## 示例

输入：`root = [1,2,3,null,5,null,4]`  
输出：`[1,3,4]`  
解释：从右侧看，每一层最右边的节点依次是 1、3、4。

## 解题思路

采用 **BFS（广度优先搜索）层序遍历**。  
使用队列按层处理节点，对于每一层，记录该层最后一个节点的值（即从右侧看到的节点）。  
因为队列天然满足“先进先出”，可以保证一层一层从左到右遍历，每层最后一个就是右视图看到的节点。  
时间复杂度 `O(n)`，空间复杂度 `O(n)`（队列最多存储一层的节点）。

## 解答

```java
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
import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // 存储右视图的节点值，用 ArrayList 因为需要按顺序添加并返回
        if (root == null) return result; // 空树直接返回空列表

        Queue<TreeNode> queue = new LinkedList<>(); // 队列用于 BFS，LinkedList 实现了 Queue 接口，支持 O(1) 入队出队
        queue.offer(root); // 根节点入队，启动层序遍历

        while (!queue.isEmpty()) { // 只要队列不为空，说明还有层未处理
            int size = queue.size(); // 记录当前层的节点个数，这个 size 是这一层要处理的节点总数
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // 从队头取出一个节点
                // 如果是当前层的最后一个节点，就加入结果（因为从右侧看只能看到最右边的）
                if (i == size - 1) {
                    result.add(node.val); // 将最后一个节点的值加入结果列表
                }
                // 将下一层的节点按从左到右的顺序入队
                if (node.left != null) queue.offer(node.left); // 左孩子入队
                if (node.right != null) queue.offer(node.right); // 右孩子入队
            }
        }
        return result; // 返回右视图节点值列表
    }
}
```
