---
title: "103. 二叉树的锯齿形层序遍历（二叉树 / BFS）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 二叉树, BFS, 中等]
category: codetop
difficulty: 中等
leetcodeId: 103
excerpt: "使用 BFS 层序遍历，并按层交替反转结果。"
---

# 103. 二叉树的锯齿形层序遍历（二叉树 / BFS）

## 题目

给你二叉树的根节点 `root`，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

## 示例

输入：`root = [3,9,20,null,null,15,7]`  
输出：`[[3], [20,9], [15,7]]`

## 解题思路

使用 **BFS（广度优先搜索）+ 队列** 进行层序遍历。  
队列天然满足“先进先出”，可以保证一层一层地处理节点。  
每处理完一层，根据当前层数决定是否反转该层结果：偶数层（从 0 开始）从左到右，奇数层从右到左。  
时间复杂度 `O(n)`，空间复杂度 `O(n)`（队列最多存一层节点）。

## 解答

```java
import java.util.*;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); // 存储最终结果，每个元素是一层的值列表
        if (root == null) return result; // 空树直接返回空结果，避免后续操作空指针

        Queue<TreeNode> queue = new LinkedList<>(); // 队列用于 BFS，LinkedList 实现了 Queue 接口，支持 O(1) 入队出队
        queue.offer(root); // 根节点入队，启动层序遍历

        boolean reverse = false; // 标记当前层是否需要反转：false 表示从左到右，true 表示从右到左

        while (!queue.isEmpty()) { // 只要队列不为空，说明还有层未处理
            int size = queue.size(); // 记录当前层的节点个数，这个 size 是这一层要处理的节点总数
            List<Integer> level = new ArrayList<>(); // 存储当前层的节点值

            for (int i = 0; i < size; i++) { // 只处理当前层的 size 个节点
                TreeNode node = queue.poll(); // 从队头取出一个节点
                level.add(node.val); // 将节点值加入当前层列表

                if (node.left != null) queue.offer(node.left); // 左孩子入队，作为下一层节点
                if (node.right != null) queue.offer(node.right); // 右孩子入队，作为下一层节点
            }

            if (reverse) { // 如果当前层需要从右到左
                Collections.reverse(level); // 直接反转列表，得到从右到左的顺序
            }
            result.add(level); // 把当前层加入最终结果
            reverse = !reverse; // 翻转标记，下一层方向相反
        }

        return result; // 返回锯齿形层序遍历结果
    }
}
```
