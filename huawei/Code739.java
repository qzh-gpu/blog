package huawei;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code739 {
    // 单调栈（标准解法） —— O(n) 时间，O(n) 空间，维护一个单调递减的索引栈。
    /*
        解法一：单调栈（正序遍历）
        数据结构选型
        使用 Deque<Integer> 作为栈，存储数组下标，而非温度值。

        栈内温度值从栈底到栈顶严格递减（保证栈顶是当前未找到下一个更高温度的“最冷”元素）。

        算法流程
        初始化结果数组 ans，长度为 n，全部置 0。

        遍历 i 从 0 到 n-1：

        当栈非空 且 当前温度 temperatures[i] 大于 栈顶索引对应的温度时：

        弹出栈顶索引 idx，计算 ans[idx] = i - idx。

        将当前索引 i 入栈。

        遍历结束后栈中剩余元素没有更高温度，保持 0。

        返回 ans。

        复杂度
        时间：O(n)，每个元素最多入栈和出栈一次。

        空间：O(n)，最坏情况栈存储所有元素（如温度递减）。
     */
    public int[] dailyTemperatures(int[] temperatures){
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // 存储下标

        for (int i = 0; i < n; i++) {
            // 当温度高于栈顶温度，说明栈找到了栈顶元素的下一个更高温度
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int idx = stack.pop();
                ans[idx] = i - idx;
            }
            stack.push(i);
        }
        return ans;
    }
}
