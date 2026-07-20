---
title: "Two Sum 两数之和 — LeetCode 1"
date: 2026-07-18
tags: [LeetCode, 华为高频, 数组, 哈希表, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 1
excerpt: "给定一个整数数组 nums 和一个目标值 target，找出数组中和为目标值的两个数的下标。使用 HashMap 将时间复杂度从 O(n²) 优化到 O(n)。"
---

# Two Sum 两数之和

## 题目描述

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** `target` 的那 **两个** 整数，并返回它们的数组下标。

假设每种输入只会对应一个答案，并且不能使用同一个元素两次。

> **示例**: `nums = [2,7,11,15]`, `target = 9` → 返回 `[0,1]`

---

## 方法一：暴力枚举

两层循环遍历所有组合。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n²)** |
| 空间 | **O(1)** |

---

## 方法二：哈希表（推荐）

遍历数组，对于每个元素 `x`，检查 `target - x` 是否已经在 HashMap 中。

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n)** |
| 空间 | **O(n)** |

---

## 总结

- HashMap 是 LeetCode 中最常用的数据结构之一，核心技巧是 **用空间换时间**
- 这道题也是 HashMap 的经典入门，面试出现频率很高
- 华为笔试中频繁出现 Hash 相关的变体题
