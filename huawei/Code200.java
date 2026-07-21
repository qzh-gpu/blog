package huawei;

public class Code200 {
    // 深度优先搜索
    // 算法流程
    // 1. 初始化岛屿计数 count = 0。
    // 2. 遍历二维数组每个格子（i, j）。
    //      若grid[i][j] == '1', 说明发现一个新的岛屿，count++。
    //      调用dfs(grid, i ,j) 将该岛屿所有相连的‘1’全部标记为‘0’(淹没)
    // 3. 返回count

    public int numIslands(char[][] grid ){
        if(grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        // 边界检查或当前格子为‘0’， 直接返回
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0'){
            return;
        }
        // 将当前陆地淹没（标记为已访问）
        grid[i][j] = '0';
        // 四个方向递归
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
