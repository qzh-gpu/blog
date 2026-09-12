---
title: "165. 比较版本号（字符串 / 双指针）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 字符串, 双指针, 中等]
category: codetop
difficulty: 中等
leetcodeId: 165
excerpt: "双指针逐段解析版本号，忽略前导零并比较修订号。"
---

# 165. 比较版本号（字符串 / 双指针）

## 题目

给你两个版本号字符串 `version1` 和 `version2`，请你比较它们。版本号由点 `.` 分隔的修订号组成。修订号的值是它转换为整数并忽略前导零。  
比较版本号时，请按从左到右的顺序依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 `0`。  
返回规则如下：

- 如果 `version1 < version2` 返回 `-1`
- 如果 `version1 > version2` 返回 `1`
- 除此之外返回 `0`

## 示例

输入：`version1 = "1.2", version2 = "1.10"`  
输出：`-1`  
解释：`version1` 的第二个修订号为 `"2"`，`version2` 的第二个修订号为 `"10"`，`2 < 10`，所以 `version1 < version2`。

输入：`version1 = "1.01", version2 = "1.001"`  
输出：`0`  
解释：忽略前导零，`"01"` 和 `"001"` 都表示整数 `1`，相同。

## 解题思路

采用**双指针逐段解析**。  
两个指针 `i` 和 `j` 分别遍历 `version1` 和 `version2`。每次循环解析出当前段的整数值（遇到 `.` 或末尾停止），然后比较两个整数。  
如果某一段已经遍历完，则其整数值为 `0`（表示缺失的修订号）。  
因为解析时直接通过 `num = num * 10 + (c - '0')` 计算，所以前导零自动被忽略。  
时间复杂度 `O(n + m)`，空间复杂度 `O(1)`。

## 解答

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(); // version1 的长度，用于控制指针 i 的边界
        int n2 = version2.length(); // version2 的长度，用于控制指针 j 的边界
        int i = 0, j = 0; // 双指针，分别遍历两个字符串

        // 只要还有任意一个字符串未处理完，就继续循环
        while (i < n1 || j < n2) {
            int num1 = 0; // 当前 version1 段的数值，初始为 0
            int num2 = 0; // 当前 version2 段的数值，初始为 0

            // 解析 version1 当前段：遇到 '.' 或字符串末尾停止
            while (i < n1 && version1.charAt(i) != '.') {
                // 将字符数字转为整数并累加，前导零自动被忽略（如 "01" 会计算为 1）
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++; // 指针后移
            }

            // 解析 version2 当前段
            while (j < n2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++; // 指针后移
            }

            // 比较当前段的数值
            if (num1 < num2) return -1; // version1 当前段较小，整体较小
            if (num1 > num2) return 1;  // version1 当前段较大，整体较大

            // 如果相等，继续处理下一段
            if (i < n1) i++; // 跳过 version1 当前段后的 '.'
            if (j < n2) j++; // 跳过 version2 当前段后的 '.'
        }

        // 所有段都相等，返回 0
        return 0;
    }
}
```
