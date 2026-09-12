---
title: "4. 寻找两个正序数组的中位数（二分查找）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 数组, 二分查找, 困难]
category: codetop
difficulty: 困难
leetcodeId: 4
excerpt: "在较短数组上二分划分，寻找两个正序数组的中位数。"
---

# 4. 寻找两个正序数组的中位数（二分查找）

## 题目

给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的中位数。  
算法的时间复杂度应该为 `O(log (m+n))`。

## 示例

输入：`nums1 = [1,3], nums2 = [2]`  
输出：`2.00000`  
解释：合并数组 = `[1,2,3]`，中位数 `2`。

输入：`nums1 = [1,2], nums2 = [3,4]`  
输出：`2.50000`  
解释：合并数组 = `[1,2,3,4]`，中位数 `(2 + 3) / 2 = 2.5`。

## 解题思路

采用**二分划分法**。  
将两个数组分别划分为左半部分和右半部分，使得：

- 左半部分的总长度 = `(m + n + 1) / 2`（向上取整，这样奇数长度时中位数就是左半部分最大值）。
- 左半部分所有元素 ≤ 右半部分所有元素。

设 `nums1` 的划分点为 `i`，则 `nums2` 的划分点 `j = totalLeft - i`。  
需要满足交叉条件：

- `nums1[i-1] <= nums2[j]`
- `nums2[j-1] <= nums1[i]`

如果不满足，则调整 `i`（二分查找）。  
为了减少二分次数，并且避免 `j` 越界，我们始终在**较短的数组**上进行二分。

时间复杂度 `O(log(min(m, n)))`，满足题目要求；空间复杂度 `O(1)`。

## 解答

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保证 nums1 是较短的数组，这样二分次数更少，且 j 不会越界
        // 如果 nums1 更长，就交换两个数组，递归调用自身
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length; // nums1 的长度
        int n = nums2.length; // nums2 的长度

        // 左半部分的总长度，向上取整。奇数时左半部分多一个，中位数就是左半部分最大值
        int totalLeft = (m + n + 1) / 2;

        // 在 nums1 的区间 [0, m] 中二分查找划分点 i
        int left = 0;
        int right = m;

        while (left <= right) {
            // i 是 nums1 左半部分的元素个数
            int i = left + (right - left) / 2;
            // j 是 nums2 左半部分的元素个数，由总长度减去 i 得到
            int j = totalLeft - i;

            // 处理边界：如果 i == 0，说明 nums1 左半部分没有元素，用负无穷表示
            // 如果 i == m，说明 nums1 右半部分没有元素，用正无穷表示
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 满足交叉条件：左半部分最大值 <= 右半部分最小值
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 总长度为奇数：中位数是左半部分的最大值
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    // 总长度为偶数：中位数是左半部分最大值和右半部分最小值的平均值
                    return (Math.max(nums1LeftMax, nums2LeftMax)
                            + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // nums1 左半部分最大值太大，说明 i 需要减小，即右边界左移
                right = i - 1;
            } else {
                // nums2 左半部分最大值太大，说明 i 需要增大，即左边界右移
                left = i + 1;
            }
        }

        // 理论上不会执行到这里，因为输入保证有解
        return 0.0;
    }
}
```
