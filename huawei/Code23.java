package huawei;

import java.util.PriorityQueue;

public class Code23 {
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
