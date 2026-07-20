---
title: RAG 场景题
---

# RAG 场景题

> 收录检索增强生成（RAG）相关的场景设计题目与解答思路。

<script setup>
import { data as posts } from '../../.vitepress/theme/posts.data.ts'
const list = posts.filter(p => p.category === 'rag')
</script>

<BlogList :posts="list" :showCategory="false" />

<div v-if="!list.length" style="text-align: center; padding: 60px 0; color: var(--vp-c-text-3);">
  暂无文章，敬请期待
</div>
