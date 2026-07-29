---
title: "无重复字符的最长子串 — LeetCode 3"
date: 2026-07-21
tags: [滑动窗口, LeetCode, 华为高频, 字符串, 滑动窗口, HashMap, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 3
excerpt: "给定一个字符串 s，找出其中不含有重复字符的最长子串的长度。使用滑动窗口 + HashMap 维护不重复子串。"
---

# 无重复字符的最长子串

## 题目描述

给定一个字符串 `s`，请你找出其中不含有重复字符的**最长子串**的长度。

## 示例

```
输入: s = "abcabcbb"
输出: 3
解释: 最长无重复字符子串是 "abc"，长度为 3。

输入: s = "bbbbb"
输出: 1

输入: s = "pwwkew"
输出: 3
解释: 最长无重复字符子串是 "wke"，长度为 3。
```

## 思路分析

> 我们维护一个 HashMap，从始至终保证其中元素不重复，因此每次遍历的长度就是我们所有子串的长度，然后一个个比较。left 只是一个随着重复元素位置改变的索引。right 的遍历每次必然判断我们左边界是否重复，然后每次依然把遍历的元素存进去，这样每次新增元素都保证不重复，同时保证所有子串都照顾到了。

## 代码实现

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int lengthOfLongestSubString(String s){
        // 我们维护一个HashMap，我们从始至终保证其中元素不重复
        // 因此他每次遍历的长度就是我们所有子串的长度，然后一个个比较。

        Map<Character, Integer> map = new HashMap<>();

        // left只是一个随着重复元素位置改变的索引

        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            // right的遍历每次必然判断我们左边界是否重复，然后每次依然把遍历的元素存进去
            // 这样每次新增元素都保证不重复，同时保证所有子串都照顾到了
            char c = s.charAt(right);
            if(map.containsKey(c) && map.get(c) >= left){
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个字符遍历一次 |
| 空间 | **O(k)** — k 为字符集大小，HashMap 存储 |
