---
title: "根据字符出现频率排序 — LeetCode 451"
date: 2026-07-29
tags: [哈希表, 排序, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 451
excerpt: "根据字符出现频率排序。"
---

# 根据字符出现频率排序

## 题目描述

给定一个字符串，将其中的字符按照出现频率从高到低重新排列。如果多个字符频率相同，它们之间的顺序不限。

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 哈希表 + 最大堆】
 * 1. 使用 HashMap 统计每个字符的出现频率。
 * 2. 将每个字符及其频率作为一个条目放入最大堆（按频率降序排列）。
 * 3. 依次从堆中弹出频率最高的字符，将该字符重复其频率次，拼接到结果字符串中。
 * 4. 因为相同字符总是连续拼接，所以保证了相同字符在一起。
 * 5. 时间复杂度 O(n + k log k)，其中 n 为字符串长度，k 为不同字符数（最多 52/128）。
 *    空间复杂度 O(n)（结果字符串和辅助结构）。
 */

import java.util.*;

class Solution {
    public String frequencySort(String s) {
        // 1. 统计每个字符出现的频率
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // 2. 创建最大堆，按频率降序排列
        //    使用 PriorityQueue，自定义比较器 (a, b) -> b.getValue() - a.getValue()
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // 3. 将所有条目加入堆
        maxHeap.addAll(freqMap.entrySet());

        // 4. 构建结果字符串
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            // 取出频率最高的条目
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char c = entry.getKey();
            int freq = entry.getValue();
            // 将该字符重复 freq 次
            for (int i = 0; i < freq; i++) {
                sb.append(c);
            }
        }

        // 5. 返回结果
        return sb.toString();
    }
}
```
