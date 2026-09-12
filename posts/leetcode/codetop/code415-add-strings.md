---
title: "415. 字符串相加（字符串 / 模拟）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 字符串, 模拟, 简单]
category: codetop
difficulty: 简单
leetcodeId: 415
excerpt: "从个位开始模拟竖式加法，并处理进位。"
---

# 415. 字符串相加（字符串 / 模拟）

## 题目

给定两个字符串形式的非负整数 `num1` 和 `num2`，计算它们的和并同样以字符串形式返回。  
你不能使用任何内建的用于处理大整数的库（比如 `BigInteger`），也不能直接将输入的字符串转换为整数形式。

## 示例

输入：`num1 = "11"`, `num2 = "123"`  
输出：`"134"`

输入：`num1 = "456"`, `num2 = "77"`  
输出：`"533"`

输入：`num1 = "0"`, `num2 = "0"`  
输出：`"0"`

## 解题思路

采用**模拟竖式加法**。  
从两个字符串的末尾（个位）开始，逐位相加，同时维护一个进位变量 `carry`。  
每次计算 `sum = 数字1 + 数字2 + carry`，当前位结果是 `sum % 10`，新的进位是 `sum / 10`。  
用 `StringBuilder` 从低位到高位依次追加结果，最后反转得到正确答案。  
时间复杂度 `O(max(m, n))`，空间复杂度 `O(max(m, n))`。

## 解答

```java
class Solution {
    public String addStrings(String num1, String num2) {
        // StringBuilder 是可变字符串，用于逐位追加结果，避免 String 拼接产生大量临时对象
        StringBuilder res = new StringBuilder();

        int i = num1.length() - 1; // 指向 num1 的末尾（个位）
        int j = num2.length() - 1; // 指向 num2 的末尾（个位）
        int carry = 0; // 进位，初始为 0

        // 只要还有数字未处理，或者还有进位，就继续循环
        while (i >= 0 || j >= 0 || carry != 0) {
            // 如果 i 还在范围内，取出对应数字字符并转为整数；否则补 0
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            // 如果 j 还在范围内，取出对应数字字符并转为整数；否则补 0
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = x + y + carry; // 当前位的总和（包含进位）

            res.append(sum % 10); // 当前位的结果是 sum 的个位数，追加到 StringBuilder
            carry = sum / 10;     // 新的进位是 sum 的十位数

            i--; // 移动到 num1 的下一位
            j--; // 移动到 num2 的下一位
        }

        // 因为是从低位往高位追加，所以需要反转才能得到正确的数字顺序
        return res.reverse().toString();
    }
}
```
