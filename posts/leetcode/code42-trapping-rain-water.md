---
title: "接雨水 — LeetCode 42"
date: 2026-07-21
tags: [LeetCode, 华为高频, 双指针, 单调栈, 困难]
category: leetcode
difficulty: 困难
leetcodeId: 42
excerpt: "给定 n 个非负整数表示柱状图，计算下雨后能接多少雨水。使用双指针，每个位置接水量取决于左右两边最短高度。"
---

# 接雨水

## 题目描述

给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

## 示例

```
输入: height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

## 思路分析

> 每个位置接水的多少取决于左右两边最短的那个长度减去自己当前的高度。

## 代码实现

```java
class Solution {
    public static int trap(int[] height){
        // 每个位置接水的多少取决于左右两边最短的那个长度减去自己当前的高度
        int left = 0, right = height.length - 1;
        int water = 0;
        int maxLeft = 0, maxright = 0;
        while(left < right){
            maxLeft = Math.max(height[left], maxLeft);
            maxright = Math.max(height[right], maxright);
            if(maxright < maxLeft ){
                water += maxright - height[right];
                right--;
            }else{
                water += maxLeft - height[left];
                left++;
            }
        }
        return water;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 双指针遍历一次 |
| 空间 | **O(1)** |
