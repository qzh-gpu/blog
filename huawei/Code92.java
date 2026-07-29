package huawei;

/*
 * 【原理说明 - 记录位置法】
 * 1. 核心需求：反转链表中从 left 到 right 之间的节点（含两端），保持其他节点顺序不变。
 * 2. 为了处理头节点被反转的情况，使用哑节点 dummy，让 dummy.next = head。
 * 3. 找到四个关键位置：
 *    - pre：left 节点的前驱（即第 left-1 个节点）。
 *    - leftNode：第 left 个节点（反转子链表的头）。
 *    - rightNode：第 right 个节点（反转子链表的尾）。
 *    - post：rightNode 的后继（第 right+1 个节点）。
 * 4. 先断开子链表：pre.next = null，rightNode.next = null。
 * 5. 反转子链表（调用标准反转函数），得到新头 newHead（原 rightNode）。
 * 6. 重新连接：pre.next = newHead，leftNode.next = post。
 * 7. 返回 dummy.next。
 * 8. 时间复杂度 O(n)，空间复杂度 O(1)。
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 1. 创建哑节点，简化头节点处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. 找到 left 的前驱节点 pre
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        // 3. 找到 left 节点（即反转子链表的起点）
        ListNode leftNode = pre.next;

        // 4. 找到 right 节点（即反转子链表的终点）
        ListNode rightNode = leftNode;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }

        // 5. 找到 right 的后继 post
        ListNode post = rightNode.next;

        // 6. 断开子链表：让 pre 和 rightNode 与子链表断开
        pre.next = null;
        rightNode.next = null;

        // 7. 反转子链表，返回新的头节点（即原 rightNode）
        ListNode newHead = reverseList(leftNode);

        // 8. 重新连接：pre 指向新头，新尾（即原 leftNode）指向 post
        pre.next = newHead;
        leftNode.next = post;

        // 9. 返回新链表头
        return dummy.next;
    }

    // 标准的反转链表函数（迭代法）
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
