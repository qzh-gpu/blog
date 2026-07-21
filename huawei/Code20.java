package huawei;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Code20 {
    // 标准栈（使用 Deque / Stack） —— 遇到左括号入栈，遇到右括号检查栈顶是否匹配。
    /*
        解法一：标准栈（Deque 作为栈）
        数据结构选型
        使用 Deque<Character> stack = new ArrayDeque<>();
        推荐用 Deque 而非 Stack，因为 Stack 是遗留类，ArrayDeque 性能更好，且支持 push/pop/peek 方法。

        使用 HashMap<Character, Character> 存储右括号到左括号的映射，方便匹配，提高代码可读性。

        算法流程
        构建映射：')' -> '(', ']' -> '[', '}' -> '{'。

        遍历字符串的每个字符 c：

        如果 c 是左括号（即 !map.containsKey(c)），则入栈。

        如果 c 是右括号：

        若栈为空，直接返回 false（无匹配左括号）。

        否则，弹出栈顶 top，检查 top 是否等于 map.get(c)，不等则返回 false。

        遍历结束后，如果栈为空，则返回 true，否则返回 false（有多余左括号）。

        复杂度
        时间：O(n)，每个字符一次入栈/出栈操作。

        空间：O(n)，最坏情况全为左括号。
     */
    public boolean isValid(String s){
        // 映射：右括号 -> 对应的左括号
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        // 使用Deque作为栈
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if(map.containsKey(c)){
                if(stack.isEmpty() || stack.pop() != map.get(c))
                return false;
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
