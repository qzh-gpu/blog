---
title: "岛屿数量 — LeetCode 200"
date: 2026-07-21
tags: [LeetCode, 华为高频, DFS, 图, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 200
excerpt: "给定一个由 '1'（陆地）和 '0'（水）组成的二维网格，计算岛屿数量。使用 DFS 淹没法标记已访问。"
---

# 岛屿数量

## 题目描述

给你一个由 `'1'`（陆地）和 `'0'`（水）组成的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，每座岛屿只能由水平/竖直方向相邻的陆地连接形成。

## 示例

```
输入: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出: 3
```

## 思路分析

> 深度优先搜索——淹没法：遇到陆地就将整个岛屿淹没（标记为已访问），避免重复计算。

> **算法流程**：
> 1. 初始化岛屿计数 count = 0。
> 2. 遍历二维数组每个格子 (i, j)。若 grid[i][j] == '1'，说明发现一个新的岛屿，count++。调用 dfs(grid, i, j) 将该岛屿所有相连的 '1' 全部标记为 '0'（淹没）。
> 3. 返回 count。

## 代码实现

```java
class Solution {
    // 深度优先搜索
    // 算法流程
    // 1. 初始化岛屿计数 count = 0。
    // 2. 遍历二维数组每个格子（i, j）。
    //      若grid[i][j] == '1', 说明发现一个新的岛屿，count++。
    //      调用dfs(grid, i ,j) 将该岛屿所有相连的'1'全部标记为'0'(淹没)
    // 3. 返回count

    public int numIslands(char[][] grid ){
        if(grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        // 边界检查或当前格子为'0'， 直接返回
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0'){
            return;
        }
        // 将当前陆地淹没（标记为已访问）
        grid[i][j] = '0';
        // 四个方向递归
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(m·n)** — 每个格子最多访问一次 |
| 空间 | **O(m·n)** — 递归栈最坏情况 |
