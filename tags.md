---
title: 标签
layout: page
---

# 标签

<script setup lang="ts">
import { data as posts } from './.vitepress/theme/posts.data.ts'
import { ref, computed } from 'vue'

const activeTag = ref('')

const tagCounts = computed(() => {
  const map: Record<string, number> = {}
  for (const p of posts) {
    for (const t of p.tags) {
      map[t] = (map[t] || 0) + 1
    }
  }
  return Object.entries(map).sort((a, b) => b[1] - a[1])
})

const filteredPosts = computed(() => {
  if (!activeTag.value) return posts
  return posts.filter(p => p.tags.includes(activeTag.value))
})
</script>

<div class="tag-cloud">
  <button
    v-for="[tag, count] in tagCounts"
    :key="tag"
    class="tag-item"
    :class="{ active: activeTag === tag }"
    @click="activeTag = activeTag === tag ? '' : tag"
  >
    {{ tag }} ({{ count }})
  </button>
</div>

<div v-if="activeTag" style="margin-bottom: 24px; color: var(--vp-c-text-2);">
  筛选标签 "<strong>{{ activeTag }}</strong>"，共 {{ filteredPosts.length }} 篇
</div>

<div v-if="filteredPosts.length" class="blog-list">
  <article v-for="post in filteredPosts" :key="post.url" class="blog-card">
    <div class="card-category">{{ post.category }}</div>
    <h2 class="card-title"><a :href="post.url">{{ post.title }}</a></h2>
    <p class="card-desc">{{ post.excerpt }}</p>
    <div class="card-meta">
      <span>{{ post.date }}</span>
      <div class="card-tags">
        <span v-for="tag in post.tags.slice(0, 3)" :key="tag" class="card-tag">{{ tag }}</span>
      </div>
    </div>
  </article>
</div>

<div v-else-if="activeTag" style="text-align: center; padding: 60px; color: var(--vp-c-text-3);">
  暂无该标签的文章
</div>
