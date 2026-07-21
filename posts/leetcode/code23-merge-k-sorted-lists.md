---
title: "合并 K 个升序链表 — LeetCode 23"
date: 2026-07-21
tags: [LeetCode, 华为高频, 堆, 优先队列, 链表, 困难]
category: leetcode
difficulty: 困难
leetcodeId: 23
excerpt: "给定 K 个升序链表，合并成一个升序链表。使用最小堆维护每个链表的当前最小节点。"
---

# 合并 K 个升序链表

## 题目描述

给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。

## 示例

```
输入: lists = [[1,4,5],[1,3,4],[2,6]]
输出: [1,1,2,3,4,4,5,6]
```

## 思路分析

> 给定 K 个升序链表，合并成一个升序链表。本质上就是多路归并问题。两种高效解法（均非暴力）：优先队列（最小堆） —— O(N·log K) 时间，O(K) 空间，用堆维护每个链表的当前最小节点，取最小值，逻辑清晰。

> **数据结构选型**：使用 `PriorityQueue<ListNode>`，自定义比较器 `(a, b) -> a.val - b.val` 实现小根堆。选择堆的理由：K 个链表当前头节点中最小的那个，一定是最终结果的下一个节点。堆可以在 O(log K) 时间内获取并移除最小值，每次插入新节点也是 O(log K)。若只用暴力遍历，每次找最小需要 O(K)，总复杂度 O(N·K)，不符合要求。

> **算法流程**：创建哑节点 dummy，指针 cur 指向它。将所有链表的头节点（非空）加入最小堆。当堆非空时：弹出堆顶节点 minNode，cur.next = minNode，cur = cur.next，如果 minNode.next 不为空，将其下一个节点加入堆。返回 dummy.next。

## 代码实现

```java
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    // 思路概览
    //给定 K 个升序链表，合并成一个升序链表。
    //本质上就是多路归并问题。两种高效解法（均非暴力）：

    //优先队列（最小堆） —— O(N·log K) 时间，O(K) 空间，用堆维护每个链表的当前最小节点，取最小值，逻辑清晰。

    // 解法一：优先队列（最小堆）
    //数据结构选型
    //使用 PriorityQueue<ListNode>，自定义比较器 (a, b) -> a.val - b.val 实现小根堆。

    //选择堆的理由：K 个链表当前头节点中最小的那个，一定是最终结果的下一个节点。堆可以在 O(log K) 时间内获取并移除最小值，每次插入新节点也是 O(log K)。

    //若只用暴力遍历，每次找最小需要 O(K)，总复杂度 O(N·K)，不符合要求。

    //算法流程
    //创建哑节点 dummy，指针 cur 指向它。

    //将所有链表的头节点（非空）加入最小堆。

    //当堆非空时：

    //弹出堆顶节点 minNode（即当前最小的节点）。

    //cur.next = minNode，cur = cur.next。

    //如果 minNode.next 不为空，将其下一个节点加入堆。

    //返回 dummy.next。

    //复杂度
    //时间：O(N·log K)，N 为所有链表的总节点数，K 为链表个数。每个节点入堆一次、出堆一次。

    //空间：O(K)，堆最多同时存储 K 个节点。
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆：根据节点值排序，值小的在堆顶
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 将所有链表的头节点加入堆
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll(); // 取出当前最小值
            cur.next = minNode;           // 拼接到结果链
            cur = cur.next;

            // 如果取出的节点还有后继，将后继加入堆
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(N·log K)** — N 为所有链表总节点数，每个节点入堆出堆各一次 |
| 空间 | **O(K)** — 堆最多同时存储 K 个节点 |
