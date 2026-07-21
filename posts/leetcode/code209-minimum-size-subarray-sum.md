---
title: "长度最小的子数组 — LeetCode 209"
date: 2026-07-21
tags: [LeetCode, 华为高频, 滑动窗口, 双指针, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 209
excerpt: "给定一个正整数数组和一个目标值 target，找出和 ≥ target 的长度最小的子数组。滑动窗口经典题。"
---

# 长度最小的子数组

## 题目描述

给定一个含有 `n` 个正整数的数组和一个正整数 `target`。找出该数组中满足其和 `≥ target` 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的子数组，返回 `0`。

## 示例

```
输入: target = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的子数组。
```

## 思路分析

> 滑动窗口：左边索引只用来求长度。一直累加，在 while 循环中减去左边的值，以寻求该情况下的最小长度，因此 while 循环中需要对 left++。寻找完这轮之后继续寻找下一组。

## 代码实现

```java
class Solution {
    public int minSubArrayLen(int[] nums, int target){
        // 依旧左边索引只用来求长度
        int left = 0, sum = 0;
        // 我们需要设置一个极大的数字，不然可能后面取最小值一直是我们设置的初始值
        int minLen = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // 一直累加，然后我们在while循环中减去左边的值。以寻求这个情况下的最小长度，因此while循环中需要对left++
            // 寻找完这轮之后继续寻找下一组
            while(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个元素最多进一次出一次 |
| 空间 | **O(1)** |
