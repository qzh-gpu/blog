package huawei;

/*
 * 【原理说明 - 计数排序】
 * 1. 题目给定 nums[i] 范围在 -10⁴ ~ 10⁴ 之间，值域大小 fixed = 20001，是一个常数。
 * 2. 我们可以用计数数组统计每个数值出现的次数，然后从最大值向最小值遍历，累加次数，直到找到第 k 个。
 * 3. 由于值域固定，计数排序的时间复杂度为 O(n + 20001) = O(n)，空间复杂度 O(20001) = O(1)（常数空间）。
 * 4. 这种方法实现简单，且性能稳定，不会出现最坏情况。
 * 5. 缺点：只适用于值域有限的场景，本题恰好适用。
 */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 1. 定义值域偏移量，将 [-10⁴, 10⁴] 映射到 [0, 20000]
        final int OFFSET = 10000;
        final int RANGE = 20001; // 从 -10000 到 10000 共 20001 个数

        // 2. 计数数组
        int[] count = new int[RANGE];

        // 3. 统计每个数值出现的次数
        for (int num : nums) {
            count[num + OFFSET]++;
        }

        // 4. 从最大值开始向下遍历，累加计数
        int remaining = k;
        for (int i = RANGE - 1; i >= 0; i--) {
            remaining -= count[i];
            if (remaining <= 0) {
                // 找到了第 k 大的数，还原真实值
                return i - OFFSET;
            }
        }

        // 理论上不会走到这里
        return -1;
    }
}