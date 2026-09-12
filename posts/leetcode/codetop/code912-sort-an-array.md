---
title: "912. 排序数组（排序 / 快速排序 / 递归）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 排序, 快速排序, 递归, 中等]
category: codetop
difficulty: 中等
leetcodeId: 912
excerpt: "使用随机基准快速排序对数组进行升序排列。"
---

# 912. 排序数组（排序 / 快速排序 / 递归）

## 题目

给你一个整数数组 `nums`，请你将该数组升序排列。  
必须在**不使用任何内置函数**的情况下解决问题，时间复杂度为 `O(n log n)`，并且空间复杂度尽可能小。

## 示例

输入：`nums = [5,2,3,1]`  
输出：`[1,2,3,5]`

输入：`nums = [5,1,1,2,0,0]`  
输出：`[0,0,1,1,2,5]`

## 解题思路

采用**快速排序（Quick Sort）**。  
快速排序是原地排序，不需要额外数组，空间复杂度仅为递归栈深度 `O(log n)`，符合“空间尽可能小”的要求。  
为了避免最坏情况（如数组已经有序时退化为 `O(n²)`），每次随机选择一个基准值 `pivot`。  
核心步骤：选基准 → 双指针从两端向中间扫描，把小于基准的放左边，大于基准的放右边 → 递归处理左右子数组。

## 解答

```java
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1); // 对整个数组进行快速排序
        return nums; // 返回排序后的数组（原地修改）
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return; // 递归终止：区间长度小于等于1，已经有序

        // 随机选择基准值，避免最坏情况（例如数组已有序）
        int pivot = nums[left + (int)(Math.random() * (right - left + 1))];

        int i = left, j = right; // 双指针：i从左向右，j从右向左
        while (i <= j) { // 当两指针未交错时继续
            while (nums[i] < pivot) i++; // 找到左边第一个 >= pivot 的元素
            while (nums[j] > pivot) j--; // 找到右边第一个 <= pivot 的元素
            if (i <= j) { // 如果两指针未交错，交换这两个元素
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++; // 交换后左指针右移
                j--; // 交换后右指针左移
            }
        }
        // 此时 j 是左半部分的右边界，i 是右半部分的左边界
        quickSort(nums, left, j);  // 递归排序左半部分
        quickSort(nums, i, right); // 递归排序右半部分
    }
}
```
