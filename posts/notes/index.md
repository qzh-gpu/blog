---
title: 随笔
---

# 随笔

> 学习心得、工具分享、技术杂谈。

<script setup>
import { data as posts } from '../../.vitepress/theme/posts.data.ts'
const notesPosts = posts.filter(p => p.category === 'notes')
</script>

<BlogList :posts="notesPosts" :showCategory="false" />

<div v-if="notesPosts.length === 0" style="text-align: center; padding: 48px; color: var(--vp-c-text-3);">
  暂无文章，敬请期待
</div>
