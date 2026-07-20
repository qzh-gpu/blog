---
layout: home

hero:
  name: "技术与面试笔记"
  text: "华为 LeetCode · RAG · Agent"
  tagline: 记录面试准备与技术积累
  actions:
    - theme: brand
      text: 开始阅读
      link: /posts/leetcode/
    - theme: alt
      text: 查看标签
      link: /tags

features:
  - icon: 🧩
    title: 华为高频 LeetCode
    details: 华为笔试面试高频算法题，含详细解法与复杂度分析
    link: /posts/leetcode/
  - icon: 🤖
    title: RAG 场景题
    details: 检索增强生成相关场景题目与解答思路
    link: /posts/rag/
  - icon: 🧠
    title: Agent 场景题
    details: 智能体架构、工具调用、多 Agent 协作等场景题
    link: /posts/agent/
---

## 最新文章

<script setup>
import { data as posts } from './.vitepress/theme/posts.data.ts'
</script>

<BlogList :posts="posts.slice(0, 6)" />
