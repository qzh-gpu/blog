/*
 * 【原理说明 - 动态规划】
 * 1. 定义 dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度。
 * 2. 对于每个 i，考虑其前面的所有 j（0 <= j < i），如果 nums[j] < nums[i]，
 *    则 nums[i] 可以接在以 nums[j] 结尾的递增子序列后面，形成更长的子序列。
 * 3. 因此状态转移方程：dp[i] = max(dp[i], dp[j] + 1) for all j < i and nums[j] < nums[i]。
 * 4. 初始时，每个元素自身可以构成长度为 1 的子序列，所以 dp[i] = 1。
 * 5. 遍历所有 i，更新全局最大长度 maxLen。
 * 6. 时间复杂度 O(n²)，空间复杂度 O(n)。
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        // 1. 边界处理：若数组为空，返回 0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // 2. dp[i] 表示以 nums[i] 结尾的最长递增子序列长度
        int[] dp = new int[n];
        // 3. 初始化：每个元素至少可以独自构成长度为 1 的子序列
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // 4. 记录全局最长长度
        int maxLen = 1;

        // 5. 外层循环：固定子序列的结尾元素 nums[i]
        for (int i = 0; i < n; i++) {
            // 6. 内层循环：检查所有在 i 之前的元素 nums[j]
            for (int j = 0; j < i; j++) {
                // 7. 如果 nums[j] < nums[i]，说明 nums[i] 可以接在 nums[j] 后面
                if (nums[j] < nums[i]) {
                    // 8. 更新 dp[i] 为更大的值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 9. 更新全局最大长度
            maxLen = Math.max(maxLen, dp[i]);
        }

        // 10. 返回结果
        return maxLen;
    }
}