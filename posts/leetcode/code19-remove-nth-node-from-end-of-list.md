---
title: "删除链表的倒数第 N 个结点 — LeetCode 19"
date: 2026-07-29
tags: [链表, 双指针, LeetCode, 华为高频, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 19
excerpt: "删除链表的倒数第 N 个结点。"
---

# 删除链表的倒数第 N 个结点

## 题目描述

给定一个链表的头节点 `head` 和整数 `n`，删除链表中倒数第 `n` 个节点，并返回删除后的链表头节点。

## 示例

```
输入: head = [1,2,3,4,5], n = 2
输出: [1,2,3,5]
```

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 双指针法】
 * 1. 要删除倒数第 n 个节点，关键是要找到它的前驱节点（即倒数第 n+1 个节点）。
 * 2. 使用两个指针 fast 和 slow，初始都指向哑节点 dummy（放在 head 前面）。
 * 3. 先让 fast 向前走 n 步，这样 fast 和 slow 之间就相隔了 n 个节点。
 * 4. 然后同时移动 fast 和 slow，每次各走一步，直到 fast 到达链表末尾（fast.next == null）。
 *    此时 slow 正好指向待删除节点的前驱（即倒数第 n+1 个节点）。
 * 5. 执行 slow.next = slow.next.next 删除目标节点。
 * 6. 为什么用哑节点？因为如果要删除的节点是头节点（n == 链表长度），没有哑节点会很难处理。
 *    哑节点让头节点的删除逻辑和其他节点完全一致。
 * 7. 时间复杂度 O(L)（L 为链表长度），空间复杂度 O(1)。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. 创建哑节点（dummy node），它的 next 指向 head
        //    哑节点简化边界处理，即使删除头节点也能统一操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. 初始化快慢指针，都指向哑节点
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 3. 先让 fast 向前走 n 步
        //    这样 fast 和 slow 之间就隔了 n 个节点
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 4. 同时移动 fast 和 slow，直到 fast 到达链表的最后一个节点（fast.next == null）
        //    此时 slow 正好指向待删除节点的前驱
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 5. 删除 slow 的后继节点（即倒数第 n 个节点）
        slow.next = slow.next.next;

        // 6. 返回新链表的头节点（dummy.next 可能就是 head，如果 head 被删除则指向新头）
        return dummy.next;
    }
}

```
