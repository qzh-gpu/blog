---
layout: home

hero:
  name: "技术博客"
  text: "Java · 系统设计 · 面试 · LeetCode"
  tagline: 记录技术成长，分享编程心得
  actions:
    - theme: brand
      text: 开始阅读
      link: /posts/leetcode/
    - theme: alt
      text: 按标签浏览
      link: /tags

features:
  - icon: 🧩
    title: LeetCode 题解
    details: 华为高频题、经典算法，含多种解法和复杂度分析
  - icon: ☕
    title: Java 深度
    details: JVM 调优、并发编程、集合源码、新特性解读
  - icon: 🏗️
    title: 系统设计
    details: 分布式架构、设计模式、微服务实战
  - icon: 📝
    title: 面试宝典
    details: 八股文整理、面经分享、高频考点
---

## 最新文章

<script setup>
import { data as posts } from './.vitepress/theme/posts.data.ts'
</script>

<BlogList :posts="posts.slice(0, 6)" />

---

## 分类导航

<div class="category-grid">
  <a href="/posts/leetcode/" class="category-card">
    <div class="cat-icon">🧩</div>
    <div class="cat-name">LeetCode</div>
    <div class="cat-count">算法题解</div>
  </a>
  <a href="/posts/java/" class="category-card">
    <div class="cat-icon">☕</div>
    <div class="cat-name">Java</div>
    <div class="cat-count">核心技术与框架</div>
  </a>
  <a href="/posts/system-design/" class="category-card">
    <div class="cat-icon">🏗️</div>
    <div class="cat-name">系统设计</div>
    <div class="cat-count">架构与设计模式</div>
  </a>
  <a href="/posts/interview/" class="category-card">
    <div class="cat-icon">📝</div>
    <div class="cat-name">面试</div>
    <div class="cat-count">面经与考点</div>
  </a>
  <a href="/posts/notes/" class="category-card">
    <div class="cat-icon">📒</div>
    <div class="cat-name">随笔</div>
    <div class="cat-count">学习心得</div>
  </a>
</div>
