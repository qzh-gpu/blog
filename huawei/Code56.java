package huawei;

/*
 * 【原理说明 - 排序 + 贪心合并】
 * 1. 区间合并的关键在于先按起始位置排序，这样所有重叠的区间必然连续排列。
 * 2. 排序后，遍历区间，维护当前合并区间的起始 start 和结束 end。
 * 3. 对于每个区间 [currStart, currEnd]：
 *    - 如果 currStart <= end，说明当前区间与之前的合并区间重叠，更新 end = max(end, currEnd)。
 *    - 否则，说明不重叠，将当前合并区间加入结果，并开始一个新的合并区间。
 * 4. 最后将最后一个合并区间加入结果。
 * 5. 时间复杂度 O(n log n)（排序），空间复杂度 O(n)（存储结果，若原地修改则为 O(1) 额外空间）。
 */

import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 1. 边界处理：如果区间数组为空或只有一个区间，直接返回原数组
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 2. 按照每个区间的起始位置升序排序
        //    使用 Arrays.sort 并传入比较器
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 3. 使用一个列表存储合并后的区间
        List<int[]> merged = new ArrayList<>();

        // 4. 初始化第一个合并区间为第一个排序后的区间
        int start = intervals[0][0];
        int end = intervals[0][1];

        // 5. 从第二个区间开始遍历
        for (int i = 1; i < intervals.length; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            // 6. 如果当前区间的起始 <= 当前合并区间的结束，说明重叠，需要合并
            if (currStart <= end) {
                // 合并：更新结束位置为较大的值
                end = Math.max(end, currEnd);
            } else {
                // 7. 不重叠：将当前合并区间加入结果
                merged.add(new int[]{start, end});
                // 8. 开始新的合并区间
                start = currStart;
                end = currEnd;
            }
        }

        // 9. 将最后一个合并区间加入结果
        merged.add(new int[]{start, end});

        // 10. 将 List 转换为二维数组返回
        return merged.toArray(new int[merged.size()][]);
    }
}