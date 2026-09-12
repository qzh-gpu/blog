---
title: "121. 买卖股票的最佳时机（贪心）"
date: 2026-09-13
tags: [CodeTop, LeetCode, 贪心, 简单]
category: codetop
difficulty: 简单
leetcodeId: 121
excerpt: "一次遍历记录最低买入价，计算单次交易最大利润。"
---

# 121. 买卖股票的最佳时机（贪心）

## 题目

给定一个数组 `prices`，它的第 `i` 个元素 `prices[i]` 表示一支给定股票第 `i` 天的价格。  
你只能选择**某一天**买入这只股票，并选择在**未来的某一个不同的日子**卖出该股票。设计一个算法来计算你所能获取的最大利润。  
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 `0`。

## 示例

输入：`prices = [7,1,5,3,6,4]`  
输出：`5`  
解释：在第 2 天（价格 = 1）买入，在第 5 天（价格 = 6）卖出，最大利润 = 6 - 1 = 5。

输入：`prices = [7,6,4,3,1]`  
输出：`0`  
解释：在这种情况下，没有交易完成，所以最大利润为 0。

## 解题思路

采用**一次遍历（贪心）**。因为只能买卖一次，且买入必须在卖出之前，我们可以在遍历过程中记录“迄今为止的最低价格” `minPrice`。  
对于每一天的价格，计算如果今天卖出的利润 `prices[i] - minPrice`，并用一个变量 `maxProfit` 记录历史最大利润。  
遍历结束后，`maxProfit` 即为答案。时间复杂度 `O(n)`，空间复杂度 `O(1)`。

## 解答

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 记录遍历过的最低买入价，初始化为最大整数，确保第一次比较时能被更新
        int maxProfit = 0; // 记录最大利润，初始为 0（因为如果不能获利，利润为 0）
        for (int i = 0; i < prices.length; i++) { // 遍历每一天的股票价格
            if (prices[i] < minPrice) { // 如果今天的价格比历史最低价还低
                minPrice = prices[i]; // 更新历史最低价（选择在今天买入更划算）
            } else if (prices[i] - minPrice > maxProfit) { // 否则，计算今天卖出的利润
                maxProfit = prices[i] - minPrice; // 如果利润超过历史最大利润，则更新
            }
        }
        return maxProfit; // 返回计算出的最大利润
    }
}
```
