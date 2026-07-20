<script setup lang="ts">
import type { Post } from '../posts.data'

defineProps<{
  posts: Post[]
  showCategory?: boolean
}>()

function getCategoryLabel(cat: string): string {
  const map: Record<string, string> = {
    leetcode: 'LeetCode',
    java: 'Java',
    'system-design': '系统设计',
    interview: '面试',
    notes: '随笔'
  }
  return map[cat] || cat
}
</script>

<template>
  <div class="blog-list">
    <article v-for="post in posts" :key="post.url" class="blog-card">
      <div v-if="showCategory !== false" class="card-category">
        {{ getCategoryLabel(post.category) }}
      </div>
      <h2 class="card-title">
        <a :href="post.url">{{ post.title }}</a>
      </h2>
      <p class="card-desc">{{ post.excerpt }}</p>
      <div class="card-meta">
        <span>{{ post.date }}</span>
        <div class="card-tags">
          <span v-for="tag in post.tags.slice(0, 3)" :key="tag" class="card-tag">{{ tag }}</span>
        </div>
      </div>
    </article>
  </div>
</template>
