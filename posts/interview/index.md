---
title: 面试
---

# 面试宝典

> 面经整理、八股文、高频考点。

<script setup>
import { data as posts } from '../../.vitepress/theme/posts.data.ts'
const interviewPosts = posts.filter(p => p.category === 'interview')
</script>

<BlogList :posts="interviewPosts" :showCategory="false" />

<div v-if="interviewPosts.length === 0" style="text-align: center; padding: 48px; color: var(--vp-c-text-3);">
  暂无文章，敬请期待
</div>
