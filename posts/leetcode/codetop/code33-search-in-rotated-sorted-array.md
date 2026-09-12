---
title: "33. 搜索旋转排序数组（二分查找）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 二分查找, 中等]
category: codetop
difficulty: 中等
leetcodeId: 33
excerpt: "在旋转排序数组中利用有序半边进行二分查找。"
---

# 33. 搜索旋转排序数组（二分查找）

## 题目

整数数组 `nums` 按升序排列，数组中的值互不相同。  
在传递给函数之前，`nums` 在预先未知的某个下标 `k` 上进行了向左旋转，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`。  
给你旋转后的数组 `nums` 和一个整数 `target`，如果 `nums` 中存在这个目标值，则返回它的下标，否则返回 `-1`。  
必须设计时间复杂度为 `O(log n)` 的算法。

## 示例

输入：`nums = [4,5,6,7,0,1,2]`, `target = 0`  
输出：`4`

输入：`nums = [4,5,6,7,0,1,2]`, `target = 3`  
输出：`-1`

## 解题思路

旋转后的数组虽然整体不是有序的，但**一定可以分成两段各自有序的子数组**。  
使用二分查找：每次取中间值 `mid`，判断 `mid` 处于哪一段有序区间，然后根据 `target` 是否在该有序区间内，决定移动 `left` 还是 `right`。  
关键：总有一半是有序的，利用有序那一半来缩小搜索范围。时间复杂度 `O(log n)`，空间 `O(1)`。

## 解答

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 左右指针，闭区间 [left, right]
        while (left <= right) { // 当区间非空时继续二分
            int mid = left + (right - left) / 2; // 取中间位置，防止 left+right 溢出
            if (nums[mid] == target) return mid; // 找到目标，直接返回下标

            if (nums[left] <= nums[mid]) { // 说明左半段 [left, mid] 是有序的
                if (target >= nums[left] && target < nums[mid]) { // target 在左半段有序区间内
                    right = mid - 1; // 收缩右边界，在左半段继续查找
                } else {
                    left = mid + 1; // 否则去右半段查找
                }
            } else { // 否则右半段 [mid, right] 是有序的
                if (target > nums[mid] && target <= nums[right]) { // target 在右半段有序区间内
                    left = mid + 1; // 收缩左边界，在右半段继续查找
                } else {
                    right = mid - 1; // 否则去左半段查找
                }
            }
        }
        return -1; // 循环结束仍未找到，返回 -1
    }
}
```
