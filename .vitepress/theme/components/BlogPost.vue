<script setup lang="ts">
defineProps<{
  title?: string
  date?: string
  tags?: string[]
  difficulty?: string
}>()
</script>

<template>
  <div class="post-header">
    <div class="post-date" v-if="date">{{ date }}</div>
    <div class="post-tags" v-if="tags && tags.length">
      <span class="post-tag" v-for="tag in tags" :key="tag">{{ tag }}</span>
      <span v-if="difficulty" class="post-difficulty" :class="difficulty.toLowerCase()">
        {{ difficulty }}
      </span>
    </div>
  </div>
</template>
