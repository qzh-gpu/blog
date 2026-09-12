---
title: "5. 最长回文子串（字符串 / 双指针）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 字符串, 双指针, 中等]
category: codetop
difficulty: 中等
leetcodeId: 5
excerpt: "使用中心扩展法寻找字符串中的最长回文子串。"
---

# 5. 最长回文子串（字符串 / 双指针）

## 题目

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

## 示例

输入：`s = "babad"`  
输出：`"bab"`（`"aba"` 同样是符合题意的答案）

输入：`s = "cbbd"`  
输出：`"bb"`

## 解题思路

采用**中心扩展法**。回文串一定有一个中心，可能是单个字符（奇数长度），也可能是两个相邻字符之间（偶数长度）。遍历字符串的每个位置，分别以该字符为中心、以该字符和下一个字符之间为中心，向两边扩展，记录能扩展到的最长回文长度。最终取全局最长的那一段。时间复杂度 O(n²)，空间复杂度 O(1)，代码简洁直观。

## 解答

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return ""; // 空串或null直接返回空字符串，避免后续越界
        int start = 0, end = 0; // 记录最长回文子串的起始和结束索引，初始为0
        for (int i = 0; i < s.length(); i++) { // 遍历每个字符作为中心点
            int len1 = expandAroundCenter(s, i, i); // 以单个字符为中心扩展（奇数长度回文）
            int len2 = expandAroundCenter(s, i, i + 1); // 以两个字符之间为中心扩展（偶数长度回文）
            int len = Math.max(len1, len2); // 取两种中心扩展得到的更长者
            if (len > end - start) { // 如果当前回文长度大于已记录的最长长度
                start = i - (len - 1) / 2; // 计算新回文起始索引：中心i减去左半边长度
                end = i + len / 2; // 计算新回文结束索引：中心i加上右半边长度
            }
        }
        return s.substring(start, end + 1); // 截取最长回文子串（substring含头不含尾，所以end+1）
    }

    private int expandAroundCenter(String s, int left, int right) { // 中心扩展函数，返回扩展后的回文长度
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { // 左右指针未越界且字符相等
            left--; // 左指针向左扩展
            right++; // 右指针向右扩展
        }
        return right - left - 1; // 跳出循环时左右指针多走了一步，实际回文长度为 right - left - 1
    }
}
```
