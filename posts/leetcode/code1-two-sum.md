---
title: "两数之和 — LeetCode 1"
date: 2026-07-29
tags: [哈希表, LeetCode, 华为高频, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 1
excerpt: "两数之和。"
---

# 两数之和

## 题目描述

给定一个整数数组 `nums` 和目标值 `target`，需要在数组中找出两个不同位置的元素，使它们的和等于 `target`，并返回这两个元素的下标。

## 示例

```
输入: nums = [2,7,11,15], target = 9
输出: [0,1]
解释: 因为 nums[0] + nums[1] == 9，返回 [0,1]。
```

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 哈希表法】
 * 1. 核心需求：在 O(n) 时间内找到两个数，使得 nums[i] + nums[j] == target。
 * 2. 暴力法需要 O(n²) 两两枚举，超时。利用哈希表可以空间换时间。
 * 3. 遍历数组时，对于当前元素 nums[i]，计算补数 complement = target - nums[i]。
 *    如果 complement 已经存在于哈希表中，说明之前某个位置 j 的值正好是 complement，那么 (j, i) 就是答案。
 *    如果不存在，则将当前 (nums[i], i) 存入哈希表，供后续元素查找。
 * 4. 为什么一次遍历就够了？因为每个元素只需要查一次补数是否在之前出现过，不需要回头匹配。
 * 5. 哈希表选择 HashMap<Integer, Integer>：键为数值，值为其下标。
 *    选择 HashMap 而非 TreeMap，因为不需要有序，且 HashMap 的 get/put 平均 O(1)。
 * 6. 时间复杂度 O(n)，空间复杂度 O(n)。
 */

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. 创建哈希表：键为数组元素值，值为该元素在数组中的索引
        Map<Integer, Integer> map = new HashMap<>();

        // 2. 遍历数组，i 是当前元素下标
        for (int i = 0; i < nums.length; i++) {
            // 3. 计算当前元素需要的补数
            int complement = target - nums[i];

            // 4. 检查补数是否已经存在于哈希表中（即之前是否出现过）
            if (map.containsKey(complement)) {
                // 5. 如果存在，则返回补数对应的下标（之前存入的）和当前下标 i
                //    题目保证有且仅有一组解，所以可以直接返回
                return new int[] { map.get(complement), i };
            }

            // 6. 如果补数不存在，将当前元素及其下标存入哈希表，供后续元素使用
            map.put(nums[i], i);
        }

        // 7. 根据题意，一定会找到解，这里 return 空数组只是占位（永远不会执行到）
        return new int[0];
    }
}
```
