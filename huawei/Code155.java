package huawei;

import java.util.ArrayDeque;
import java.util.Deque;

public class Code155 {
    // 思路概览
    //设计一个栈，支持常规的 push、pop、top，并要求在 O(1) 时间内返回栈内最小值。

    //普通栈无法直接 O(1) 取最小值（需要遍历）。我们利用空间换时间：

    //辅助栈（同步记录当前最小值） —— 用另一个栈同步存储每一步的当前最小值，查询时直接取栈顶。

    //数据结构选型
    //主栈 stack：存储所有 push 的元素。

    //辅助栈 minStack：存储 每个状态下的当前最小值。

    //当 push(x) 时，若 minStack 为空或 x <= minStack.peek()，则将 x 也压入 minStack。

    //当 pop() 时，若 stack.pop() 的值等于 minStack.peek()，则 minStack 也弹出。

    //getMin() 直接返回 minStack.peek()。
    private Deque<Integer> stack;     // 主栈
    private Deque<Integer> minStack;  // 辅助栈，记录每个状态的最小值

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        // 如果 minStack 为空，或 val <= 当前最小值，则压入辅助栈
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // 如果弹出的值正好是当前最小值，辅助栈也要弹出
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
