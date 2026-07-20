---
title: "反转链表 — LeetCode 206"
date: 2026-07-16
tags: [LeetCode, 华为高频, 链表, 递归, 迭代, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 206
excerpt: "反转一个单链表，分别用迭代法和递归法实现，理解链表指针操作的核心思想。"
---

# 反转链表

## 题目描述

给你单链表的头节点 `head`，请你反转链表，并返回反转后的链表。

> **示例**: `1 → 2 → 3 → 4 → 5 → null` → `5 → 4 → 3 → 2 → 1 → null`

---

## 方法一：迭代法（双指针）

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;  // 已反转部分的头
        ListNode curr = head;  // 当前处理的节点

        while (curr != null) {
            ListNode next = curr.next; // 暂存下一个
            curr.next = prev;          // 反转指针
            prev = curr;               // prev 前移
            curr = next;               // curr 前移
        }
        return prev; // prev 指向新链表的头
    }
}
```

**过程图解**：

```
初始:  null ← prev    curr → 1 → 2 → 3 → null
一轮:  null ← 1 ← prev    curr → 2 → 3 → null
二轮:  null ← 1 ← 2 ← prev    curr → 3 → null
三轮:  null ← 1 ← 2 ← 3 ← prev    curr = null
返回 prev
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n)** — 每个节点访问一次 |
| 空间 | **O(1)** — 只用了三个指针 |

---

## 方法二：递归法

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        // 终止条件：空节点或单个节点
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转后续链表
        ListNode newHead = reverseList(head.next);
        // 反转当前节点与下一个节点的指向
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```

| 复杂度 |  |
|--------|--------|
| 时间 | **O(n)** |
| 空间 | **O(n)** — 递归调用栈 |

---

## 总结

- **迭代法** 是面试首选，O(1) 空间，体现对指针操控的理解
- **递归法** 代码简洁，但要注意栈溢出风险
- 华为笔试链表题出现频率很高，反转链表是链表操作的基础
