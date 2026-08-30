---
title: "跳跃游戏 — LeetCode 55"
date: 2026-07-29
tags: [贪心, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 55
excerpt: "跳跃游戏。"
---

# 跳跃游戏

## 题目描述

给定一个非负整数数组 `nums`，数组中的每个元素表示在该位置最多可以向前跳几步。判断是否能够从第一个位置到达最后一个位置。

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 贪心】
 * 1. 维护一个变量 maxReach，表示当前能够到达的最远下标。
 * 2. 遍历数组，对于每个位置 i：
 *    - 如果 i > maxReach，说明当前位置已经不可达，直接返回 false（剪枝）。
 *    - 否则，更新 maxReach = max(maxReach, i + nums[i])。
 * 3. 如果 maxReach 已经 >= 最后一个下标，提前返回 true。
 * 4. 因为只需要一次遍历，时间复杂度 O(n)，空间复杂度 O(1)。
 * 5. 贪心正确性：只要每一步都尽可能延伸最远距离，就能覆盖所有可达位置。
 */

class Solution {
    public boolean canJump(int[] nums) {
        // 1. 初始化最远可达位置为 0（起点）
        int maxReach = 0;
        // 2. 获取数组长度
        int n = nums.length;

        // 3. 遍历数组（注意：只需要遍历到 n-1 即可，但提前终止更高效）
        for (int i = 0; i < n; i++) {
            // 4. 如果当前位置已经超过了能到达的最远位置，说明无法继续前进
            if (i > maxReach) {
                return false;
            }

            // 5. 更新最远可达位置：取当前最远和从 i 出发能跳到的最远位置中的较大者
            maxReach = Math.max(maxReach, i + nums[i]);

            // 6. 如果已经能到达最后一个位置，提前返回 true（优化，可选）
            if (maxReach >= n - 1) {
                return true;
            }
        }

        // 7. 循环结束，如果能到达最后一个位置（但上面提前返回了），或者永远不会执行到这里
        //    但为了语法完整，返回 true（实际上遍历完且没返回 false，说明可达）
        return true;
    }
}
```
