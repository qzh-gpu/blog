---
title: "二叉树的层序遍历 — LeetCode 102"
date: 2026-07-29
tags: [二叉树, BFS, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 102
excerpt: "二叉树的层序遍历。"
---

# 二叉树的层序遍历

## 题目描述

给定一棵二叉树的根节点 `root`，按照从上到下、每层从左到右的顺序，返回每一层节点值组成的列表。

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - BFS 队列法】
 * 1. 层序遍历的本质是按“层”从上到下、从左到右访问节点。
 * 2. 使用队列（Queue）存储当前层的所有节点，初始先将根节点入队。
 * 3. 每次处理一层时，先记录当前队列的大小 size（即当前层的节点数）。
 * 4. 循环 size 次，从队列中取出节点，将其值加入当前层的列表，并将其左右子节点（若非空）依次入队。
 * 5. 处理完一层后，将该层列表加入结果列表。
 * 6. 当队列为空时，所有节点都已遍历完毕。
 * 7. 时间复杂度 O(n)（每个节点入队出队一次），空间复杂度 O(n)（队列最多存储一层的节点，最坏情况为满二叉树的最后一层，约 n/2 个节点）。
 */

import java.util.*;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1. 结果列表，存储每一层的节点值列表
        List<List<Integer>> result = new ArrayList<>();
        // 2. 如果根节点为空，直接返回空列表
        if (root == null) {
            return result;
        }

        // 3. 创建队列，用于存储当前层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 4. 根节点入队
        queue.offer(root);

        // 5. 当队列不为空时，表示还有层未处理
        while (!queue.isEmpty()) {
            // 6. 记录当前队列大小（即当前层的节点数）
            int size = queue.size();
            // 7. 创建当前层的列表
            List<Integer> level = new ArrayList<>();

            // 8. 循环 size 次，处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                // 9. 从队列中取出队头节点
                TreeNode node = queue.poll();
                // 10. 将节点值加入当前层列表
                level.add(node.val);

                // 11. 如果左子节点非空，加入队列（下一层）
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // 12. 如果右子节点非空，加入队列（下一层）
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 13. 将当前层列表加入结果列表
            result.add(level);
        }

        // 14. 返回结果
        return result;
    }
}

```
