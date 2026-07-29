package huawei;

/*
 * 【原理说明 - 迭代法】
 * 1. 两个链表都是逆序存储数字（个位在链表头），相加时正好可以从头开始逐位相加，符合加法从个位开始的习惯。
 * 2. 维护一个进位变量 carry，初始为 0。
 * 3. 同时遍历两个链表，每次取出两个节点的值（若某链表已空则取 0），加上进位，得到当前位的和 sum。
 * 4. 新节点的值为 sum % 10，新的进位为 sum / 10。
 * 5. 使用哑节点 dummy 简化结果链表的构建，避免对头节点做空判断。
 * 6. 遍历结束后，如果进位 carry > 0，还需额外创建一个值为 carry 的节点。
 * 7. 时间复杂度 O(max(m, n))，空间复杂度 O(max(m, n))（结果链表长度）。
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. 创建哑节点，作为结果链表的头节点的前驱
        ListNode dummy = new ListNode(0);
        // 2. cur 指向当前结果链表的最后一个节点，初始指向哑节点
        ListNode cur = dummy;
        // 3. 进位，初始为 0
        int carry = 0;

        // 4. 只要 l1 或 l2 还有节点，或者还有进位，就继续循环
        while (l1 != null || l2 != null || carry != 0) {
            // 5. 获取当前位的值：若链表已空则取 0
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // 6. 计算当前位的和（两个数字 + 进位）
            int sum = val1 + val2 + carry;

            // 7. 更新进位
            carry = sum / 10;

            // 8. 创建新节点，值为 sum % 10（当前位的数字）
            cur.next = new ListNode(sum % 10);
            // 9. 移动 cur 指针到新节点
            cur = cur.next;
            // 10. 移动 l1 和 l2 指针（如果还有下一个节点）
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 11. 返回哑节点的下一个节点（即结果链表的头节点）
        return dummy.next;
    }
}
