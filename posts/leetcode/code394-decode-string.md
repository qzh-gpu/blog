---
title: "字符串解码 — LeetCode 394"
date: 2026-07-21
tags: [LeetCode, 华为高频, 栈, 字符串, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 394
excerpt: "给定形如 3[a2[c]] 的编码字符串，解码并返回原始字符串。使用双栈（数字栈 + 字符串栈）处理嵌套结构。"
---

# 字符串解码

## 题目描述

给定一个经过编码的字符串，返回它解码后的字符串。编码规则为 `k[encoded_string]`，表示其中方括号内部的 `encoded_string` 正好重复 `k` 次。

## 示例

```
输入: s = "3[a]2[bc]"
输出: "aaabcbc"

输入: s = "3[a2[c]]"
输出: "accaccacc"
```

## 思路分析

> 题目给出形如 `k[encoded_string]` 的编码，要求解码后重复相应次数。核心是处理嵌套结构，比如 `3[a2[c]]` 需要先解内层再解外层。

> **双栈迭代法（数字栈 + 字符串栈）**——遇到 `[` 时保存当前状态，遇到 `]` 时弹出并重复拼接，非常通用。

> **数据结构选型**：`Deque<Integer>` numStack 存储遇到 `[` 时的重复次数。`Deque<String>` strStack 存储遇到 `[` 时之前的解码结果。StringBuilder cur 当前正在构建的字符串片段。遍历字符时，用 num 累计数字（可能多位）。

> **算法流程**：初始化 num = 0，cur = new StringBuilder()，两个栈。遍历每个字符 c：若 c 是数字则 num = num * 10 + (c - '0')；若 c 是字母则 cur.append(c)；若 c 是 '['，将 num 压入数字栈、cur.toString() 压入字符串栈，重置 num 和 cur；若 c 是 ']'，弹出 repeatTimes 和 prev，将 cur 重复 repeatTimes 次拼接到 prev 后面。遍历结束返回 cur.toString()。

## 代码实现

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 题目给出形如 k[encoded_string] 的编码，要求解码后重复相应次数。
    //核心是处理嵌套结构，比如 3[a2[c]] 需要先解内层再解外层。

    //两种高效解法（均 O(n)，n 为输出字符串长度）：

    //双栈迭代法（数字栈 + 字符串栈） —— 遇到 [ 时保存当前状态，遇到 ] 时弹出并重复拼接，非常通用。

    /*
        解法一：双栈迭代法
        数据结构选型
        Deque<Integer> numStack：存储遇到 [ 时的重复次数。

        Deque<String> strStack：存储遇到 [ 时之前的解码结果。

        StringBuilder cur：当前正在构建的字符串片段。

        遍历字符时，用 num 累计数字（可能多位）。

        算法流程
        初始化 num = 0，cur = new StringBuilder()，两个栈。

        遍历每个字符 c：

        若 c 是数字（0~9）：num = num * 10 + (c - '0')。

        若 c 是字母：cur.append(c)。

        若 c 是 '['：

        将 num 压入数字栈，cur.toString() 压入字符串栈。

        重置 num = 0，cur = new StringBuilder()。

        若 c 是 ']'：

        弹出 repeatTimes = numStack.pop()，弹出 prev = strStack.pop()。

        将 cur 重复 repeatTimes 次，拼接到 prev 后面。

        cur = new StringBuilder(prev)。

        遍历结束，返回 cur.toString()。

        复杂度
        时间：O(n)，每个字符和输出字符均处理一次。

        空间：O(n)，栈存储中间状态。
     */
    public String decodeString(String s){
        // 数字栈：存放'['前的重复次数
        Deque<Integer> numStack = new ArrayDeque<>();
        // 字符串栈：存放'['前已累积的字符串
        Deque<StringBuilder> strStack = new ArrayDeque<>();

        StringBuilder cur = new StringBuilder(); // 当前正在构建的字符串
        int num = 0; // 当前数字，可能是多位

        for (char c : s.toCharArray()) {
            if(Character.isDigit(c)){ // 判断当前字符是否为数字
                // 数字可能有多位，累积
                num = num * 10 + (c - '0');
            }else if(c == '['){
                // 遇到 '['，将当前数字和字符串分别入栈
                numStack.push(num);
                strStack.push(cur);
                // 重置，准备处理括号之中的内容
                num = 0;
                cur = new StringBuilder();
            }else if(c == ']'){
                // 遇到 ']'，弹出重复次数和之前的字符串
                int repeat = numStack.pop();
                StringBuilder prev = strStack.pop();
                // 将当前 cur 重复 repeat 次，追加到 prev 后面
                for (int i = 0; i < repeat; i++) {
                    prev.append(cur);
                }
                cur = prev;
            }else{
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 每个字符和输出字符均处理一次 |
| 空间 | **O(n)** — 栈存储中间状态 |
