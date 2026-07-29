---
title: "盛最多水的容器 — LeetCode 11"
date: 2026-07-21
tags: [双指针, LeetCode, 华为高频, 双指针, 贪心, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 11
excerpt: "给定一个数组 height，找出两条线之间可以容纳最多水的容器。使用双指针从两端向中间收缩。"
---

# 盛最多水的容器

## 题目描述

给定一个长度为 `n` 的整数数组 `height`。有 `n` 条垂线，找出其中两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

## 示例

```
输入: height = [1,8,6,2,5,4,8,3,7]
输出: 49
解释: 垂直线分别代表高度 [1,8,6,2,5,4,8,3,7]，最大容器面积 = 7 × 7 = 49。
```

## 思路分析

> 我们从两端开始，保证我们的宽最长，然后后面每次移动宽肯定减小，所以想找大面积只能是某一条边变长，因此我们遍历每一条边，直至左右重合。

## 代码实现

```java
class Solution {
    public static int maxArea(int[] height){
        // 思路： 我们从两端开始，保证我们的宽最长，然后后面每次移动宽肯定减小，所以
        // 想找大面积只能是某一条边变长，因此我们遍历每一条边，直至左右重合。
        int left = 0, right = height.length - 1;
        int area = 0;
        int maxArea = 0;
        while (left < right){
            int h = Math.min(height[left], height[right]);
            area = h * (right - left);
            maxArea = Math.max(area, maxArea);
            if(height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxArea;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个元素最多访问一次 |
| 空间 | **O(1)** |
