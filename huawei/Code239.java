package huawei;

import java.util.ArrayDeque;
import java.util.Deque;
// 维护一个单调递减的对了，保持当前最大值始终在队头，每次去替换队尾的元素。
public class Code239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 边界情况：空数组或窗口大小为0，直接返回空数组
        if (nums.length == 0 || k == 0) return new int[0];

        int n = nums.length;
        // 结果数组长度 = 窗口滑动次数 = n - k + 1
        int[] res = new int[n - k + 1];

        // 双端队列，存储数组下标（而不是值），方便判断过期
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 【步骤1：维护单调递减队列】
            // 如果队尾元素 <= 当前元素，说明队尾元素又小又老，永远不可能是后续窗口的最大值
            // 从队尾依次踢掉这些“弱者”，保持队列从队头到队尾严格递减
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();  // 从后门踢掉
            }

            // 当前元素入队（从后门进），此时队列仍然保持递减
            deque.offerLast(i);

            // 【步骤2：移除过期元素】
            // 如果队头元素的下标已经滑出当前窗口（即 <= i - k），说明它已过期
            // 从队头踢掉（因为队头是最大值，过期了必须移除）
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();  // 从前门踢掉
            }

            // 【步骤3：记录当前窗口最大值】
            // 当 i 走到 k-1 时，第一个窗口完整形成，之后每个 i 都对应一个完整窗口
            // 队头元素就是当前窗口的最大值（因为队列递减，队头最大）
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
