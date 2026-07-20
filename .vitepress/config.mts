import { defineConfig } from 'vitepress'

export default defineConfig({
  title: '技术与面试笔记',
  description: '华为高频 LeetCode · RAG 场景题 · Agent 场景题',
  lang: 'zh-CN',
  base: '/blog/',

  markdown: {
    theme: 'one-dark-pro',
    lineNumbers: true,
    languages: ['java', 'python', 'cpp', 'javascript', 'typescript', 'go', 'sql', 'bash']
  },

  themeConfig: {
    search: { provider: 'local' },

    nav: [
      { text: '首页', link: '/' },
      { text: '标签', link: '/tags' },
      {
        text: '分类',
        items: [
          { text: '华为高频 LeetCode', link: '/posts/leetcode/' },
          { text: 'RAG 场景题', link: '/posts/rag/' },
          { text: 'Agent 场景题', link: '/posts/agent/' }
        ]
      }
    ],

    sidebar: {
      '/posts/leetcode/': [
        { text: '华为高频 LeetCode', link: '/posts/leetcode/', items: [] }
      ],
      '/posts/rag/': [
        { text: 'RAG 场景题', link: '/posts/rag/', items: [] }
      ],
      '/posts/agent/': [
        { text: 'Agent 场景题', link: '/posts/agent/', items: [] }
      ]
    },

    socialLinks: [
      { icon: 'github', link: 'https://github.com/qzh-gpu' }
    ],

    footer: {
      message: '基于 VitePress 构建',
      copyright: 'Copyright © 2026'
    },

    lastUpdated: {
      text: '最后更新于',
      formatOptions: { dateStyle: 'short', timeStyle: 'short' }
    }
  }
})
