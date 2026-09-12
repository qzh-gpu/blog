---
title: "82. 删除排序链表中的重复元素 II（链表 / 双指针）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 链表, 双指针, 中等]
category: codetop
difficulty: 中等
leetcodeId: 82
excerpt: "使用哑节点和双指针删除排序链表中所有重复值节点。"
---

# 82. 删除排序链表中的重复元素 II（链表 / 双指针）

## 题目

给定一个已排序的链表的头节点 `head`，删除原始链表中所有重复数字的节点，只留下不同的数字。返回已排序的链表。

## 示例

输入：`head = [1,2,3,3,4,4,5]`  
输出：`[1,2,5]`

输入：`head = [1,1,1,2,3]`  
输出：`[2,3]`

## 解题思路

因为头节点也可能被删除，所以使用**哑节点（dummy）** 来简化边界处理。  
用两个指针：`prev` 指向最后一个确定不重复的节点，`cur` 用于遍历链表。  
当发现 `cur` 与 `cur.next` 值相同时，说明有重复，记录下这个重复值，然后让 `cur` 一直向后走，跳过所有等于该值的节点。最后让 `prev.next` 指向 `cur`，从而一次性删除所有重复节点。  
如果没有重复，则 `prev` 和 `cur` 都向后移动一步。  
时间复杂度 `O(n)`，空间复杂度 `O(1)`。

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
    public ListNode deleteDuplicates(ListNode head) {
        // 创建哑节点，因为头节点也可能被删除，用哑节点统一操作
        ListNode dummy = new ListNode(0);
        dummy.next = head; // 哑节点指向原头节点

        ListNode prev = dummy; // prev 指向最后一个确定不重复的节点
        ListNode cur = head;   // cur 用于遍历链表

        while (cur != null) { // 遍历整个链表
            // 如果当前节点与下一个节点值相同，说明有重复
            if (cur.next != null && cur.val == cur.next.val) {
                int dupVal = cur.val; // 记录重复的值
                // 跳过所有值为 dupVal 的节点
                while (cur != null && cur.val == dupVal) {
                    cur = cur.next; // cur 向后移动
                }
                // 此时 cur 指向第一个不等于 dupVal 的节点，直接让 prev.next 指向它
                prev.next = cur;
            } else {
                // 没有重复，prev 和 cur 都向后移动一步
                prev = cur;
                cur = cur.next;
            }
        }
        // 返回哑节点的下一个节点，即新链表的头节点
        return dummy.next;
    }
}
```
