---
title: "反转链表 — LeetCode 206"
date: 2026-07-29
tags: [链表, LeetCode, 华为高频, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 206
excerpt: "反转链表。"
---

# 反转链表

## 题目描述

给定单链表的头节点 `head`，将整个链表反转，并返回反转后的头节点。

## 示例

```
输入: head = [1,2,3,4,5]
输出: [5,4,3,2,1]
```

## 代码实现

```java
package huawei;

/*
 * 【原理说明 - 迭代法】
 * 1. 核心思想：遍历链表，逐个改变每个节点的 next 指针方向，让它指向前一个节点。
 * 2. 为什么需要三个指针？
 *    - prev：指向前一个节点（初始为 null，因为新头节点反转后要指向 null）。
 *    - curr：指向当前正在处理的节点（初始为 head）。
 *    - nextTemp：临时保存 curr.next，防止我们改变 curr.next 时丢失掉后续的链表节点。
 * 3. 循环过程：每次将 curr.next 指向 prev，然后将 prev 和 curr 整体向后移动一位。
 * 4. 当 curr 变为 null 时，说明遍历结束，此时 prev 正好指向原链表的尾节点，即反转后的新头节点。
 * 5. 时间复杂度 O(n)，空间复杂度 O(1)（原地反转）。
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
    public ListNode reverseList(ListNode head) {
        // 1. 定义三指针：prev 指向前一个节点，初始为 null（因为新的尾节点要指向 null）
        ListNode prev = null;
        // 2. curr 指向当前处理的节点，从头节点开始
        ListNode curr = head;
        // 3. 临时指针，用于保存 curr 的下一个节点，防止断链
        ListNode nextTemp = null;

        // 4. 当当前节点不为空时，一直循环处理
        while (curr != null) {
            // 5. 先保存当前节点的下一个节点（如果不保存，修改 next 后就找不到了）
            nextTemp = curr.next;
            // 6. 核心反转操作：将当前节点的 next 指向前一个节点（方向反转）
            curr.next = prev;
            // 7. prev 向前移动：将 prev 更新为当前节点
            prev = curr;
            // 8. curr 向前移动：将 curr 更新为之前保存的下一个节点
            curr = nextTemp;
        }

        // 9. 循环结束时，curr 为 null，prev 指向原链表的最后一个节点，即反转后的新头节点
        return prev;
    }
}

```
