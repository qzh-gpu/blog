---
title: 华为高频 LeetCode
---

# 华为高频 LeetCode

> 收录华为笔试面试高频算法题，按解法分类，共 **38** 题。

<script setup>
import { data as posts } from '../../.vitepress/theme/posts.data.ts'
const lc = posts.filter(p => p.category === 'leetcode')
const byTag = (tag) => lc.filter(p => p.tags.includes(tag))
</script>

## 双指针

<BlogList :posts="byTag('双指针')" :showCategory="false" />

## 链表

<BlogList :posts="byTag('链表')" :showCategory="false" />

## 哈希表

<BlogList :posts="byTag('哈希表')" :showCategory="false" />

## 滑动窗口

<BlogList :posts="byTag('滑动窗口')" :showCategory="false" />

## 栈 / 单调栈

<BlogList :posts="byTag('栈')" :showCategory="false" />

## DFS / 图

<BlogList :posts="byTag('DFS')" :showCategory="false" />

## 二叉树

<BlogList :posts="byTag('二叉树')" :showCategory="false" />

## 动态规划

<BlogList :posts="byTag('动态规划')" :showCategory="false" />

## 回溯

<BlogList :posts="byTag('回溯')" :showCategory="false" />

## 贪心

<BlogList :posts="byTag('贪心')" :showCategory="false" />

## 数组 / 排序

<BlogList :posts="byTag('数组').concat(byTag('排序')).filter((post, index, arr) => arr.findIndex(p => p.url === post.url) === index)" :showCategory="false" />

## 二分查找

<BlogList :posts="byTag('二分查找')" :showCategory="false" />

## 堆 / 优先队列

<BlogList :posts="byTag('堆')" :showCategory="false" />

## 快慢指针

<BlogList :posts="byTag('快慢指针')" :showCategory="false" />
