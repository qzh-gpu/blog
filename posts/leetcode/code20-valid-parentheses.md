---
title: "有效的括号 — LeetCode 20"
date: 2026-07-21
tags: [LeetCode, 华为高频, 栈, 字符串, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 20
excerpt: "给定一个只包含括号的字符串，判断是否有效。使用栈匹配左右括号，Deque 性能优于 Stack。"
---

# 有效的括号

## 题目描述

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串 `s`，判断字符串是否有效。

有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合。

## 示例

```
输入: s = "()"
输出: true

输入: s = "()[]{}"
输出: true

输入: s = "(]"
输出: false
```

## 思路分析

> 标准栈（使用 Deque / Stack）—— 遇到左括号入栈，遇到右括号检查栈顶是否匹配。

> **数据结构选型**：使用 `Deque<Character>` 而非 `Stack`，因为 Stack 是遗留类，ArrayDeque 性能更好，且支持 push/pop/peek 方法。使用 HashMap 存储右括号到左括号的映射，方便匹配，提高代码可读性。

> **算法流程**：构建映射 `')' -> '('`, `']' -> '['`, `'}' -> '{'`。遍历字符串每个字符：如果是左括号则入栈；如果是右括号，若栈为空直接返回 false，否则弹出栈顶检查是否匹配。遍历结束后栈为空则返回 true。

## 代码实现

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    // 标准栈（使用 Deque / Stack） —— 遇到左括号入栈，遇到右括号检查栈顶是否匹配。
    /*
        解法一：标准栈（Deque 作为栈）
        数据结构选型
        使用 Deque<Character> stack = new ArrayDeque<>();
        推荐用 Deque 而非 Stack，因为 Stack 是遗留类，ArrayDeque 性能更好，且支持 push/pop/peek 方法。

        使用 HashMap<Character, Character> 存储右括号到左括号的映射，方便匹配，提高代码可读性。

        算法流程
        构建映射：')' -> '(', ']' -> '[', '}' -> '{'。

        遍历字符串的每个字符 c：

        如果 c 是左括号（即 !map.containsKey(c)），则入栈。

        如果 c 是右括号：

        若栈为空，直接返回 false（无匹配左括号）。

        否则，弹出栈顶 top，检查 top 是否等于 map.get(c)，不等则返回 false。

        遍历结束后，如果栈为空，则返回 true，否则返回 false（有多余左括号）。

        复杂度
        时间：O(n)，每个字符一次入栈/出栈操作。

        空间：O(n)，最坏情况全为左括号。
     */
    public boolean isValid(String s){
        // 映射：右括号 -> 对应的左括号
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        // 使用Deque作为栈
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if(map.containsKey(c)){
                if(stack.isEmpty() || stack.pop() != map.get(c))
                return false;
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个字符一次入栈/出栈操作 |
| 空间 | **O(n)** — 最坏情况全为左括号 |
