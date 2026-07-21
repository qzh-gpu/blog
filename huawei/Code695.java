package huawei;

public class Code695 {
    /*
        DFS（深度优先搜索 + 淹没法） —— 遇到陆地就 DFS 淹没整个岛屿并返回面积，递归实现最简洁
        解法一：DFS（递归淹没法）
        数据结构选型
        直接修改原 grid，将访问过的 1 改为 0，无需额外 visited 数组，空间最优。

        用递归实现深度优先搜索，代码最简洁。

        算法流程
        初始化 maxArea = 0。

        遍历每个格子 (i, j)：

        若 grid[i][j] == 1，调用 dfs(grid, i, j) 返回该岛屿面积，并更新 maxArea。

        dfs 函数：

        若越界或当前格子是 0，返回 0。

        将当前格子淹没（置 0）。

        返回 1 + 四个方向的 dfs 返回值之和。

        返回 maxArea。

        复杂度
        时间：O(m·n)，每个格子最多访问一次。

        空间：O(m·n)，递归栈最坏情况（整个网格全为陆地时）。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // 计算当前岛屿面积，更新最大值
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 边界检查 + 遇到水（或已淹没）返回 0
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        // 淹没当前格子（标记为已访问）
        grid[i][j] = 0;
        // 当前格子面积 1 + 四个方向的面积之和
        return 1 + dfs(grid, i - 1, j)
                + dfs(grid, i + 1, j)
                + dfs(grid, i, j - 1)
                + dfs(grid, i, j + 1);
    }

}
