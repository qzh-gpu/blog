package huawei;

/*
 * 【原理说明 - 二维DP】
 * 1. 定义 dp[i][j] 表示将 word1 的前 i 个字符转换成 word2 的前 j 个字符所需的最少操作数。
 * 2. 三种操作对应三种状态转移：
 *    - 插入：dp[i][j] = dp[i][j-1] + 1（在 word1 末尾插入一个字符匹配 word2[j-1]）
 *    - 删除：dp[i][j] = dp[i-1][j] + 1（删除 word1 的第 i 个字符）
 *    - 替换：dp[i][j] = dp[i-1][j-1] + 1（如果 word1[i-1] != word2[j-1]）
 *    如果 word1[i-1] == word2[j-1]，则 dp[i][j] = dp[i-1][j-1]（无需操作）
 * 3. 初始条件：
 *    - dp[0][j] = j（空串转换为 word2 前 j 个字符，需要插入 j 次）
 *    - dp[i][0] = i（word1 前 i 个字符转换为空串，需要删除 i 次）
 * 4. 时间复杂度 O(m×n)，空间复杂度 O(m×n)。
 */

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 1. dp[i][j] 表示 word1 前 i 个字符到 word2 前 j 个字符的最小编辑距离
        int[][] dp = new int[m + 1][n + 1];

        // 2. 初始化边界：空串转换
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 删除 i 个字符
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 插入 j 个字符
        }

        // 3. 填充 dp 表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 4. 如果当前字符相等，不需要额外操作
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 5. 否则取三种操作的最小值 + 1
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],      // 删除 word1[i-1]
                            Math.min(
                                    dp[i][j - 1],  // 插入 word2[j-1]
                                    dp[i - 1][j - 1] // 替换 word1[i-1] 为 word2[j-1]
                            )
                    );
                }
            }
        }

        // 6. 返回右下角结果
        return dp[m][n];
    }
}
