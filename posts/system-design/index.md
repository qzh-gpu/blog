---
title: 系统设计
---

# 系统设计

> 分布式架构、设计模式、微服务实战。

<script setup>
import { data as posts } from '../../.vitepress/theme/posts.data.ts'
const designPosts = posts.filter(p => p.category === 'system-design')
</script>

<BlogList :posts="designPosts" :showCategory="false" />

<div v-if="designPosts.length === 0" style="text-align: center; padding: 48px; color: var(--vp-c-text-3);">
  暂无文章，敬请期待
</div>
