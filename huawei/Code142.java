package huawei;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Code142 {
    // 经典解法是 Floyd判圈算法(快慢指针)，分两步
    // 1，判断是否有环：快慢指针每次走两步，慢指针每次走一步。若相遇则有环
    // 2，找环路口，将快指针（或慢指针）重置为头节点，然后两指针每次各走一步，再次相遇的位置即为环入口
    public ListNode detectCycle(ListNode head){
        if(head == null || head.next == null) return null;

        ListNode slow = head, fast = head;

        // 第一步判断是否有环
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        // 如果因为fast == null而退出，说明无环
        if(fast == null || fast.next == null){
            return null;
        }

        // 第二步，找环的入口

        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
