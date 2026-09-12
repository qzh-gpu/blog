---
title: "螺旋矩阵 — LeetCode 54"
date: 2026-07-29
tags: [数组, 模拟, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 54
excerpt: "螺旋矩阵。"
---

# 螺旋矩阵

## 题目描述

给定一个二维矩阵，按照从外到内、顺时针螺旋的顺序，返回矩阵中的所有元素。

## 示例

```
输入: matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出: [1,2,3,6,9,8,7,4,5]
```

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 按层模拟（边界收缩）】
 * 1. 用四个变量 top、bottom、left、right 分别表示当前未遍历区域的上下左右边界。
 * 2. 按顺时针方向依次遍历：上边（从左到右）、右边（从上到下）、下边（从右到左）、左边（从下到上）。
 * 3. 每遍历完一条边，对应边界向内收缩一步（top++，right--，bottom--，left++）。
 * 4. 当 left > right 或 top > bottom 时，所有元素都已遍历完毕。
 * 5. 时间复杂度 O(m×n)，空间复杂度 O(1)（不含输出数组）。
 */

import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 1. 结果列表
        List<Integer> result = new ArrayList<>();
        // 2. 边界处理：若矩阵为空，返回空列表
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        // 3. 初始化四个边界
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // 4. 当边界未交叉时，继续遍历
        while (top <= bottom && left <= right) {
            // 5. 遍历上边：从左到右，行固定为 top
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++; // 上边界下移

            // 6. 遍历右边：从上到下，列固定为 right
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--; // 右边界左移

            // 7. 检查是否还有行需要遍历（防止只剩一行时重复遍历）
            if (top <= bottom) {
                // 遍历下边：从右到左，行固定为 bottom
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--; // 下边界上移
            }

            // 8. 检查是否还有列需要遍历（防止只剩一列时重复遍历）
            if (left <= right) {
                // 遍历左边：从下到上，列固定为 left
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++; // 左边界右移
            }
        }

        // 9. 返回结果
        return result;
    }
}
```
