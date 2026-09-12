---
title: "25. K 个一组翻转链表（链表）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 链表, 困难]
category: codetop
difficulty: 困难
leetcodeId: 25
excerpt: "每 k 个节点一组翻转链表，不足 k 个的尾部节点保持原顺序。"
---

# 25. K 个一组翻转链表（链表）

## 题目

给你链表的头节点 `head`，每 `k` 个节点一组进行翻转，返回修改后的链表。如果节点总数不是 `k` 的整数倍，最后剩余的节点保持原有顺序。不能只改变节点内部的值，必须实际进行节点交换。

## 示例

输入：`head = [1,2,3,4,5]`, `k = 2`  
输出：`[2,1,4,3,5]`

## 解题思路

用哑节点 `dummy` 简化头节点翻转。`pre` 指向当前组的前驱，`end` 从 `pre` 出发向前走 `k` 步找到本组末尾。如果不足 `k` 个，直接结束；否则记下本组起点 `start` 和下一组起点 `next`，切断本组，用标准迭代法反转，再把反转后的新头接到 `pre` 后面，原 `start` 变成组尾，接回 `next`。然后 `pre` 移到组尾，`end` 重置，继续下一组。

## 解答

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); // 哑节点：头节点可能被翻转，用哑节点统一操作，避免特判
        dummy.next = head; // 哑节点指向原头，保持链表完整
        ListNode pre = dummy; // pre 始终指向当前组的前驱节点，初始为哑节点
        ListNode end = dummy; // end 用于探路，从 pre 开始走 k 步找组尾

        while (end.next != null) { // 只要 end 后面还有节点，就说明可能还有完整的一组
            for (int i = 0; i < k && end != null; i++) { // 尝试向前走 k 步
                end = end.next; // 一步一步移动 end
            }
            if (end == null) break; // 剩余节点不足 k 个，按题目要求保持原顺序，直接结束

            ListNode start = pre.next; // 当前组的第一个节点，即要反转的子链表头
            ListNode next = end.next; // 下一组的第一个节点，先保存，反转后要接回
            end.next = null; // 切断当前组与后面链表的连接，方便独立反转

            pre.next = reverse(start); // 反转当前组，返回新头节点，接到 pre 后面
            start.next = next; // 反转后 start 变成组尾，把它接到下一组的头 next 上
            pre = start; // pre 移到当前组尾，作为下一组的前驱
            end = pre; // end 也重置到 pre，继续探路
        }
        return dummy.next; // 返回哑节点的下一个节点，即新链表的头
    }

    private ListNode reverse(ListNode head) { // 标准反转单链表
        ListNode pre = null; // 反转后的前驱，初始为 null，因为新尾要指向 null
        ListNode cur = head; // 当前要处理的节点
        while (cur != null) { // 遍历整个子链表
            ListNode tmp = cur.next; // 先保存下一个节点，因为 cur.next 马上会被改变
            cur.next = pre; // 反转指针方向，指向前面
            pre = cur; // pre 前进到当前节点
            cur = tmp; // cur 前进到原来保存的下一个节点
        }
        return pre; // 循环结束时 pre 指向反转后的新头
    }
}
```
