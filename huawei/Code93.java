package huawei;

/*
 * 【原理说明 - 回溯法】
 * 1. IP 地址由四段组成，每段是 1~3 位数字。
 * 2. 使用深度优先搜索（DFS），从左到右尝试从字符串中截取 1~3 个字符作为当前段。
 * 3. 剪枝条件：
 *    - 剩余字符数必须在剩余段数的合理范围内（每段至少1位，最多3位）。
 *    - 当前截取的子串必须是一个有效的段（无前导零，且数值在 0~255 之间）。
 * 4. 当拼满四段且正好用完所有字符时，将当前组合加入结果列表。
 * 5. 回溯：撤销选择，尝试其他截取长度。
 * 6. 时间复杂度 O(3^4) = O(81)，因为每段最多3种长度，递归深度固定为4，实际非常高效。
 */

import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        // 长度不在 4~12 之间，不可能组成有效 IP（每段1~3位）
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        // 开始回溯，当前段索引为0，已构建的路径为空
        backtrack(s, 0, 0, new StringBuilder(), result);
        return result;
    }

    /**
     * 回溯函数
     * @param s       原始字符串
     * @param start   当前待处理字符的起始索引
     * @param segment 已经确定的段数（0~4）
     * @param path    当前构建的 IP 字符串（带点分隔）
     * @param result  结果列表
     */
    private void backtrack(String s, int start, int segment, StringBuilder path, List<String> result) {
        // 1. 如果已经确定了四段
        if (segment == 4) {
            // 且恰好用完所有字符，则是一个有效 IP
            if (start == s.length()) {
                // 注意：path 最后会多一个点，需要去掉
                result.add(path.substring(0, path.length() - 1));
            }
            return;
        }

        // 2. 剪枝：剩余的字符数必须满足剩余段数的要求（每段至少1位，最多3位）
        int remainingChars = s.length() - start;
        int remainingSegments = 4 - segment;
        // 如果剩余字符数 < 剩余段数（至少1位）或 > 剩余段数 * 3（最多3位），则不可能完成
        if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
            return;
        }

        // 3. 尝试截取 1 到 3 个字符
        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            // 截取当前段
            String part = s.substring(start, start + len);

            // 4. 检查当前段是否有效
            if (!isValidSegment(part)) {
                continue; // 无效则跳过，尝试其他长度
            }

            // 5. 记录当前段的长度，用于回溯时恢复
            int pathLen = path.length();

            // 6. 将当前段加入路径，并添加点（除了最后一段，但最后一段我们也会加，最终去掉末尾点）
            path.append(part).append('.');

            // 7. 递归处理下一段
            backtrack(s, start + len, segment + 1, path, result);

            // 8. 回溯：删除刚才添加的 part 和点
            path.setLength(pathLen);
        }
    }

    /**
     * 判断一个字符串是否为有效的 IP 段
     * 条件：无前导零（除非是单独一个 "0"），且数值在 0~255 之间
     */
    private boolean isValidSegment(String part) {
        // 长度不在 1~3 之间，无效
        if (part.length() < 1 || part.length() > 3) {
            return false;
        }
        // 如果有前导零，即长度大于1且第一个字符是 '0'，无效
        if (part.length() > 1 && part.charAt(0) == '0') {
            return false;
        }
        // 解析为整数，检查是否在 0~255 之间
        int value = Integer.parseInt(part);
        return value >= 0 && value <= 255;
    }
}
