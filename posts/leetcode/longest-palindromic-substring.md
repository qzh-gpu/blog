---
title: "最长回文子串 — LeetCode 5"
date: 2026-07-17
tags: [LeetCode, 华为高频, 字符串, 动态规划, 中心扩散, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 5
excerpt: "给定一个字符串 s，找出 s 中最长的回文子串。介绍中心扩散法和动态规划两种解法。"
---

# 最长回文子串

## 题目描述

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

> **示例**: `s = "babad"` → `"bab"`（`"aba"` 也是有效答案）

---

## 方法一：中心扩散法（推荐）

回文串是对称的，从每个位置（或两个位置之间）向两边扩散。

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // 奇数长度回文（以 i 为中心）
            int len1 = expandAroundCenter(s, i, i);
            // 偶数长度回文（以 i 和 i+1 之间为中心）
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()
               && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 实际回文长度
    }
}
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n²)** — 每个中心扩散一次 |
| 空间 | **O(1)** |

---

## 方法二：动态规划

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        boolean[][] dp = new boolean[n][n]; // dp[i][j] = s[i..j] 是否为回文
        int start = 0, maxLen = 1;

        // 单个字符都是回文
        for (int i = 0; i < n; i++) dp[i][i] = true;

        // 按长度从小到大填充
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) {
                        dp[i][j] = true; // "aa" 型
                    } else {
                        dp[i][j] = dp[i + 1][j - 1]; // 看内部子串
                    }
                }

                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n²)** |
| 空间 | **O(n²)** — DP 数组 |

---

## 总结

| 方法 | 时间 | 空间 | 推荐场景 |
|------|------|------|----------|
| 中心扩散 | O(n²) | O(1) | **面试首选**，代码简洁，空间最优 |
| 动态规划 | O(n²) | O(n²) | 理解 DP 思想，适合学习 |

- 华为笔试题中回文相关题目变化多，中心扩散法是核心技术
