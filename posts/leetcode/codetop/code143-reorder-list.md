---
title: "143. 重排链表（链表 / 快慢指针 / 反转链表）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 链表, 快慢指针, 反转链表, 中等]
category: codetop
difficulty: 中等
leetcodeId: 143
excerpt: "找中点、反转后半部分，再交替合并完成链表重排。"
---

# 143. 重排链表（链表 / 快慢指针 / 反转链表）

## 题目

给定一个单链表 `L` 的头节点 `head`，单链表 `L` 表示为：  
`L0 → L1 → … → Ln-1 → Ln`  
请将其重新排列后变为：  
`L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …`  
不能只是单纯地改变节点内部的值，而是需要实际进行节点交换。

## 示例

输入：`head = [1,2,3,4]`  
输出：`[1,4,2,3]`

输入：`head = [1,2,3,4,5]`  
输出：`[1,5,2,4,3]`

## 解题思路

采用**三步经典解法**，空间复杂度 `O(1)`：

1. **找中点**：用快慢指针找到链表中间节点，将链表分成前后两半。
2. **反转后半部分**：将后半部分链表原地反转。
3. **交替合并**：将前半部分和反转后的后半部分交替拼接。

时间复杂度 `O(n)`，空间复杂度 `O(1)`。  
**为什么不用数组？** 题目要求实际交换节点，且经典解法追求 `O(1)` 空间，数组需要 `O(n)` 额外空间。

## 解答

```java
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return; // 空链表或只有一个节点，无需重排

        // ========== 第一步：快慢指针找中点 ==========
        ListNode slow = head; // 慢指针，每次走一步
        ListNode fast = head; // 快指针，每次走两步
        while (fast.next != null && fast.next.next != null) { // 当快指针还能走两步时
            slow = slow.next; // 慢指针前进一格
            fast = fast.next.next; // 快指针前进两格
        }
        // 循环结束后，slow 指向前半部分的最后一个节点（对于偶数节点，指向前半最后一个；奇数节点，指向中间节点）

        // ========== 第二步：反转后半部分 ==========
        ListNode second = slow.next; // second 指向后半部分的第一个节点
        slow.next = null; // 将前半部分与后半部分断开，避免合并时出现环

        ListNode prev = null; // 反转后的前驱节点，初始为 null
        ListNode cur = second; // 当前要反转的节点
        while (cur != null) { // 遍历后半部分
            ListNode next = cur.next; // 先保存下一个节点，因为 cur.next 马上会被改变
            cur.next = prev; // 反转指针方向，指向前驱
            prev = cur; // 前驱前进到当前节点
            cur = next; // 当前节点前进到保存的下一个节点
        }
        // 循环结束后，prev 指向反转后的后半部分头节点

        // ========== 第三步：交替合并两个链表 ==========
        ListNode first = head; // first 指向前半部分头节点
        second = prev; // second 指向反转后的后半部分头节点
        while (second != null) { // 后半部分长度 <= 前半部分，以 second 为循环条件
            ListNode nextFirst = first.next; // 保存前半部分的下一个节点
            ListNode nextSecond = second.next; // 保存后半部分的下一个节点

            first.next = second; // 将 first 的下一个节点指向 second
            second.next = nextFirst; // 将 second 的下一个节点指向 nextFirst

            first = nextFirst; // first 前进到前半部分的下一个节点
            second = nextSecond; // second 前进到后半部分的下一个节点
        }
        // 合并完成，无需返回值，链表已原地修改
    }
}
```
