---
title: "160. 相交链表（链表 / 双指针）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 链表, 双指针, 简单]
category: codetop
difficulty: 简单
leetcodeId: 160
excerpt: "两个指针分别走完自己的链表后切换到对方链表，最终在交点或 null 相遇。"
---

# 160. 相交链表（链表 / 双指针）

## 题目

给你两个单链表的头节点 `headA` 和 `headB`，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null`。  
题目数据保证整个链式结构中不存在环。  
注意，函数返回结果后，链表必须保持其原始结构。

## 示例

输入：`intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3`  
输出：返回值为 8 的节点（即两个链表相交于节点 8）

输入：`intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2`  
输出：`null`（两个链表不相交）

## 解题思路

采用**双指针走对方的路（浪漫相遇法）**。  
两个指针 `pA` 和 `pB` 分别从 `headA` 和 `headB` 出发，各自走到链表末尾后，**切换到对方的链表头继续走**。  
这样，两个指针走过的总路程相等（都是 `lenA + lenB`）。

- 如果两个链表相交，它们一定会在交点相遇。
- 如果两个链表不相交，它们会同时走到 `null`，此时返回 `null`。

时间复杂度 `O(m + n)`，空间复杂度 `O(1)`。

## 解答

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界判断：任一链表为空，则不可能相交
        if (headA == null || headB == null) return null;

        ListNode pA = headA; // pA 从 headA 出发
        ListNode pB = headB; // pB 从 headB 出发

        // 两个指针一直走，直到相遇（即指向同一个节点）或同时为 null（不相交）
        // 为什么这样一定能相遇？因为两个指针走的总路程相等（lenA + lenB）
        while (pA != pB) {
            // pA 走完自己的链表后，切换到 headB 继续走；若已经为 null，则保持 null
            // 用三元运算符保证切换只发生一次：走到 null 后就不再切换
            pA = (pA == null) ? headB : pA.next;
            // pB 同理，走完自己的链表后，切换到 headA 继续走
            pB = (pB == null) ? headA : pB.next;
        }

        // 循环结束时，要么 pA == pB 指向相交节点，要么两者都为 null（不相交）
        // 直接返回 pA 即可，因为此时 pA 和 pB 相同
        return pA;
    }
}
```
