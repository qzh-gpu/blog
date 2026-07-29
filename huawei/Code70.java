package huawei;

/*
 * 【原理说明 - 动态规划】
 * 1. 到达第 n 级台阶，有两种方式：
 *    a. 从第 n-1 级台阶走 1 步。
 *    b. 从第 n-2 级台阶走 2 步。
 * 2. 因此递推公式：dp[n] = dp[n-1] + dp[n-2]。
 * 3. 初始条件：dp[0] = 1（站在原地算一种方法），但更常见的是 dp[1]=1, dp[2]=2。
 *    我们设定 dp[0]=1, dp[1]=1，则 dp[2]=dp[1]+dp[0]=2，符合。
 * 4. 由于只需要前两个状态，可以用两个变量滚动更新，空间复杂度 O(1)。
 * 5. 时间复杂度 O(n)，空间复杂度 O(1)。
 */

class Solution {
    public int climbStairs(int n) {
        // 1. 边界情况：只有 1 级台阶时
        if (n <= 2) {
            return n;
        }

        // 2. 初始化前两个状态
        int prev2 = 1; // 对应 dp[0]（0 级台阶的方法数，认为有 1 种）
        int prev1 = 1; // 对应 dp[1]（1 级台阶的方法数）

        // 3. 从第 2 级开始递推到第 n 级
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2; // dp[i] = dp[i-1] + dp[i-2]
            // 滚动更新
            prev2 = prev1;
            prev1 = current;
        }

        // 4. 返回结果
        return prev1;
    }
}