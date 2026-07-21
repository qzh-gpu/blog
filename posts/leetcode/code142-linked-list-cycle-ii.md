---
title: "环形链表 II — LeetCode 142"
date: 2026-07-21
tags: [LeetCode, 华为高频, 链表, 快慢指针, Floyd判圈, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 142
excerpt: "给定一个链表，返回环的入口节点。使用 Floyd 判圈算法（快慢指针），分两步：判断有环 + 找环入口。"
---

# 环形链表 II

## 题目描述

给定一个链表的头节点 `head`，返回链表开始入环的第一个节点。如果链表无环，则返回 `null`。

## 示例

```
输入: head = [3,2,0,-4], pos = 1
输出: 返回索引为 1 的链表节点
解释: 链表中有一个环，其尾部连接到第二个节点。
```

## 思路分析

> 经典解法是 Floyd 判圈算法（快慢指针），分两步：
> 1. 判断是否有环：快指针每次走两步，慢指针每次走一步。若相遇则有环。
> 2. 找环入口：将快指针（或慢指针）重置为头节点，然后两指针每次各走一步，再次相遇的位置即为环入口。

## 代码实现

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    // 经典解法是 Floyd判圈算法(快慢指针)，分两步
    // 1，判断是否有环：快慢指针每次走两步，慢指针每次走一步。若相遇则有环
    // 2，找环路口，将快指针（或慢指针）重置为头节点，然后两指针每次各走一步，再次相遇的位置即为环入口
    public ListNode detectCycle(ListNode head){
        if(head == null || head.next == null) return null;

        ListNode slow = head, fast = head;

        // 第一步判断是否有环
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        // 如果因为fast == null而退出，说明无环
        if(fast == null || fast.next == null){
            return null;
        }

        // 第二步，找环的入口

        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(n)** — 两阶段各遍历一次 |
| 空间 | **O(1)** — 只用了两个指针 |
