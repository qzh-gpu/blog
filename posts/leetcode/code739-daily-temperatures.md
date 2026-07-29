---
title: "每日温度 — LeetCode 739"
date: 2026-07-21
tags: [栈, LeetCode, 华为高频, 单调栈, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 739
excerpt: "给定一个温度数组，对于每一天，计算需要等待多少天才会有更高的温度。使用单调递减栈。"
---

# 每日温度

## 题目描述

给定一个整数数组 `temperatures`，表示每天的温度，返回一个数组 `answer`，其中 `answer[i]` 是指对于第 `i` 天，下一个更高温度出现在几天后。如果之后都不会升高，用 `0` 来代替。

## 示例

```
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
```

## 思路分析

> 单调栈（标准解法）——O(n) 时间，O(n) 空间，维护一个单调递减的索引栈。

> **数据结构选型**：使用 `Deque<Integer>` 作为栈，存储数组下标，而非温度值。栈内温度值从栈底到栈顶严格递减（保证栈顶是当前未找到下一个更高温度的"最冷"元素）。

> **算法流程**：初始化结果数组 ans，长度为 n，全部置 0。遍历 i 从 0 到 n-1：当栈非空且当前温度大于栈顶索引对应的温度时，弹出栈顶索引 idx，计算 ans[idx] = i - idx。将当前索引 i 入栈。遍历结束后栈中剩余元素没有更高温度，保持 0。

## 代码实现

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 单调栈（标准解法） —— O(n) 时间，O(n) 空间，维护一个单调递减的索引栈。
    /*
        解法一：单调栈（正序遍历）
        数据结构选型
        使用 Deque<Integer> 作为栈，存储数组下标，而非温度值。

        栈内温度值从栈底到栈顶严格递减（保证栈顶是当前未找到下一个更高温度的"最冷"元素）。

        算法流程
        初始化结果数组 ans，长度为 n，全部置 0。

        遍历 i 从 0 到 n-1：

        当栈非空 且 当前温度 temperatures[i] 大于 栈顶索引对应的温度时：

        弹出栈顶索引 idx，计算 ans[idx] = i - idx。

        将当前索引 i 入栈。

        遍历结束后栈中剩余元素没有更高温度，保持 0。

        返回 ans。

        复杂度
        时间：O(n)，每个元素最多入栈和出栈一次。

        空间：O(n)，最坏情况栈存储所有元素（如温度递减）。
     */
    public int[] dailyTemperatures(int[] temperatures){
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // 存储下标

        for (int i = 0; i < n; i++) {
            // 当温度高于栈顶温度，说明栈找到了栈顶元素的下一个更高温度
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int idx = stack.pop();
                ans[idx] = i - idx;
            }
            stack.push(i);
        }
        return ans;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个元素最多入栈和出栈一次 |
| 空间 | **O(n)** — 最坏情况栈存储所有元素 |
