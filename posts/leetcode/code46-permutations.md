---
title: "全排列 — LeetCode 46"
date: 2026-07-29
tags: [回溯, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 46
excerpt: "全排列。"
---

# 全排列

## 题目描述

给定一个不含重复数字的数组 `nums`，返回其中所有可能的排列结果。

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 回溯法】
 * 1. 全排列问题：给定不含重复数字的数组，生成所有可能的排列。
 * 2. 使用深度优先搜索（DFS）+ 回溯，每次从未使用过的元素中选择一个加入当前排列。
 * 3. 维护一个布尔数组 used，标记每个元素是否已被使用。
 * 4. 当前路径 path 存储已选元素，当路径长度等于 nums.length 时，将路径加入结果。
 * 5. 递归返回时撤销选择（将 used[i] 设为 false，并从 path 中移除最后一个元素）。
 * 6. 由于数组元素不重复，无需额外去重。
 * 7. 时间复杂度 O(n × n!)，空间复杂度 O(n)（递归栈 + 辅助数组，不含结果存储）。
 */

import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // 1. 结果列表
        List<List<Integer>> result = new ArrayList<>();
        // 2. 边界处理
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 3. 标记数组，初始全为 false（未使用）
        boolean[] used = new boolean[nums.length];
        // 4. 当前路径
        List<Integer> path = new ArrayList<>();

        // 5. 从第一个位置开始回溯
        backtrack(nums, used, path, result);

        // 6. 返回结果
        return result;
    }

    // 回溯函数
    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        // 1. 如果路径长度等于数组长度，说明已经生成一个完整排列
        if (path.size() == nums.length) {
            // 注意：这里要 new 一个 ArrayList 存入，因为 path 后续会变化
            result.add(new ArrayList<>(path));
            return;
        }

        // 2. 遍历所有元素
        for (int i = 0; i < nums.length; i++) {
            // 3. 如果当前元素已被使用，跳过
            if (used[i]) {
                continue;
            }

            // 4. 选择当前元素：标记为已使用，加入路径
            used[i] = true;
            path.add(nums[i]);

            // 5. 递归处理下一个位置
            backtrack(nums, used, path, result);

            // 6. 回溯：撤销选择，恢复状态
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
```
