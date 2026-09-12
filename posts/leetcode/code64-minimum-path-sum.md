---
title: "最小路径和 — LeetCode 64"
date: 2026-07-29
tags: [动态规划, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 64
excerpt: "最小路径和。"
---

# 最小路径和

## 题目描述

给定一个只包含非负整数的网格，从左上角走到右下角，每次只能向右或向下移动一步，求路径上数字之和的最小值。

## 示例

```
输入: grid = [[1,3,1],[1,5,1],[4,2,1]]
输出: 7
解释: 路径 1 -> 3 -> 1 -> 1 -> 1 的总和最小。
```

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 动态规划】
 * 1. 定义 dp[i][j] 为从左上角 (0,0) 到达 (i,j) 的最小路径和。
 * 2. 因为只能向右或向下移动，所以到达 (i,j) 的方式只有两种：
 *    a. 从上方 (i-1, j) 向下移动一步
 *    b. 从左方 (i, j-1) 向右移动一步
 * 3. 状态转移方程：dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
 * 4. 边界处理：
 *    - 第一行：只能从左边来，dp[0][j] = grid[0][j] + dp[0][j-1]
 *    - 第一列：只能从上面来，dp[i][0] = grid[i][0] + dp[i-1][0]
 * 5. 可以直接在原 grid 上修改，空间复杂度 O(1)。
 * 6. 时间复杂度 O(m×n)，空间复杂度 O(1)（原地修改）。
 */

class Solution {
    public int minPathSum(int[][] grid) {
        // 1. 获取行数和列数
        int m = grid.length;
        int n = grid[0].length;

        // 2. 处理第一行：只能从左边移动到右边
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        // 3. 处理第一列：只能从上面移动到下面
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // 4. 处理中间部分（从 (1,1) 开始到右下角）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 5. 状态转移：当前值 += 上方和左方中较小的路径和
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        // 6. 返回右下角的值（即最小路径和）
        return grid[m - 1][n - 1];
    }
}
```
