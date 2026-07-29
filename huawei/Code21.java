package huawei;

/*
 * 【原理说明 - 迭代法】
 * 1. 题目要求合并两个有序链表，保持升序。这类似于归并排序中的合并步骤。
 * 2. 核心思路：使用两个指针分别遍历两个链表，每次都选取值较小的节点追加到结果链表中，然后移动对应指针。
 * 3. 使用哑节点（dummy node）简化结果链表的构建，避免对头节点做空判断。
 * 4. 当其中一个链表遍历完毕时，直接将另一个链表的剩余部分接到结果链表末尾（因为剩余部分已经有序且都大于之前的所有值）。
 * 5. 时间复杂度 O(m + n)（m、n 分别为两个链表的长度），空间复杂度 O(1)（只使用了几个指针，没有额外分配新节点）。
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 1. 创建哑节点，作为结果链表的头节点的前驱
        ListNode dummy = new ListNode(0);
        // 2. cur 指向结果链表的最后一个节点，初始指向哑节点
        ListNode cur = dummy;

        // 3. 同时遍历两个链表，直到其中一个链表遍历完
        while (l1 != null && l2 != null) {
            // 4. 比较两个链表当前节点的值
            if (l1.val <= l2.val) {
                // 5. 如果 l1 的值 <= l2 的值，将 l1 的当前节点接到结果链表末尾
                cur.next = l1;
                // 6. l1 指针向后移动一位
                l1 = l1.next;
            } else {
                // 7. 否则将 l2 的当前节点接到结果链表末尾
                cur.next = l2;
                // 8. l2 指针向后移动一位
                l2 = l2.next;
            }
            // 9. cur 指针向后移动一位（指向刚刚追加的节点）
            cur = cur.next;
        }

        // 10. 当其中一个链表为空时，直接将另一个链表的剩余部分接到结果链表末尾
        //     因为剩余部分已经是有序的，且都大于之前所有值
        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }

        // 11. 返回哑节点的下一个节点（即合并后链表的头节点）
        return dummy.next;
    }
}
