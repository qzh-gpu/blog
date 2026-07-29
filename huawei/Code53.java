package huawei;

/*
 * 【原理说明 - Kadane 算法】
 * 1. 遍历数组，维护两个变量：
 *    - currentSum：以当前元素结尾的最大子数组和。
 *    - maxSum：全局最大子数组和。
 * 2. 对于每个元素 num，要么将其加入之前的子数组（currentSum + num），
 *    要么重新开始一个新的子数组（num），取两者较大者作为新的 currentSum。
 * 3. 更新 maxSum 为 currentSum 和 maxSum 中的较大者。
 * 4. 因为只需要一次遍历，时间复杂度 O(n)，空间复杂度 O(1)。
 * 5. 适用于包含负数的数组，因为负数会被丢弃（如果当前累计和变为负数，则从下一个元素重新开始）。
 */

class Solution {
    public int maxSubArray(int[] nums) {
        // 1. 初始化当前子数组和为第一个元素
        int currentSum = nums[0];
        // 2. 初始化全局最大和为第一个元素
        int maxSum = nums[0];

        // 3. 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // 4. 决定是继续扩展当前子数组，还是重新开始
            //    如果 currentSum + num < num，说明 currentSum 为负数，拉低总和，不如重新开始
            currentSum = Math.max(currentSum + num, num);

            // 5. 更新全局最大值
            maxSum = Math.max(maxSum, currentSum);
        }

        // 6. 返回结果
        return maxSum;
    }
}