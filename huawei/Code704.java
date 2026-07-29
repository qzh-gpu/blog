package huawei;

/*
 * 【原理说明 - 标准二分查找（左闭右闭区间）】
 * 1. 二分查找要求数组有序（本题为升序）。
 * 2. 维护搜索区间 [left, right]，初始为 [0, n-1]。
 * 3. 每次取中间位置 mid = left + (right - left) / 2，比较 nums[mid] 与 target：
 *    - 若相等，返回 mid。
 *    - 若 nums[mid] < target，说明目标在右半部分，left = mid + 1。
 *    - 若 nums[mid] > target，说明目标在左半部分，right = mid - 1。
 * 4. 循环条件为 left <= right，因为区间左右闭合，当 left == right 时仍需检查该位置。
 * 5. 若循环结束仍未找到，返回 -1。
 * 6. 时间复杂度 O(log n)，空间复杂度 O(1)。
 */

class Solution {
    public int search(int[] nums, int target) {
        // 1. 初始化左右指针，区间为 [left, right]
        int left = 0;
        int right = nums.length - 1;

        // 2. 当区间不为空时，继续查找
        while (left <= right) {
            // 3. 计算中间位置，防止 left + right 溢出
            int mid = left + (right - left) / 2;

            // 4. 比较中间值与目标值
            if (nums[mid] == target) {
                // 找到了，返回下标
                return mid;
            } else if (nums[mid] < target) {
                // 目标在右半部分，左指针右移
                left = mid + 1;
            } else {
                // 目标在左半部分，右指针左移
                right = mid - 1;
            }
        }

        // 5. 未找到，返回 -1
        return -1;
    }
}