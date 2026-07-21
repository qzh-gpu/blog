---
title: "三数之和 — LeetCode 15"
date: 2026-07-21
tags: [LeetCode, 华为高频, 双指针, 排序, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 15
excerpt: "给定一个整数数组 nums，找出所有和为 0 且不重复的三元组。先排序，再固定一个数，用双指针找另外两个数。"
---

# 三数之和

## 题目描述

给你一个整数数组 `nums`，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k`，同时还满足 `nums[i] + nums[j] + nums[k] == 0`。返回所有和为 `0` 且不重复的三元组。

## 示例

```
输入: nums = [-1,0,1,2,-1,-4]
输出: [[-1,-1,2],[-1,0,1]]
解释: 不同的三元组和为 0。
```

## 思路分析

> 首先这个排序很重要，这个直接影响了后面我们的循环判断。外层循环跳过重复的固定和，内层循环固定总和去找与和数互为相反数的总和。重复跳过一定要用 while，因为 if 只会跳过一个重复的，但如果三个连续重复则会少跳过一个。

## 代码实现

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        // 首先这个排序很重要，这个直接影响了后面我们的循环判断
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            // 这个是跳过0索引之后的重复，跳过的是我们固定的和。
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int target = -nums[i];
            int left = i + 1, right = n - 1;
            // 这个是内层循环，我们固定总和，然后去找与和数互为相反数的总和
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum == target){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 这个一定要是while，因为用if只会跳过一个重复的，但如果三个连续重复则会少跳过一个
                   while(left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n²)** — 排序 O(n log n) + 双指针 O(n²) |
| 空间 | **O(1)** — 不计返回结果 |
