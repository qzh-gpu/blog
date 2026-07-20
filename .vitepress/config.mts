import { defineConfig } from 'vitepress'

export default defineConfig({
  title: '技术博客',
  description: '记录学习文档：LeetCode、Java、系统设计、面试',
  lang: 'zh-CN',

  markdown: {
    theme: 'one-dark-pro',
    lineNumbers: true,
    languages: ['java', 'python', 'cpp', 'javascript', 'typescript', 'go', 'sql', 'bash', 'rust']
  },

  themeConfig: {
    // 搜索
    search: {
      provider: 'local'
    },

    // 导航栏
    nav: [
      { text: '首页', link: '/' },
      { text: '标签', link: '/tags' },
      {
        text: '分类',
        items: [
          { text: 'LeetCode', link: '/posts/leetcode/' },
          { text: 'Java', link: '/posts/java/' },
          { text: '系统设计', link: '/posts/system-design/' },
          { text: '面试', link: '/posts/interview/' },
          { text: '随笔', link: '/posts/notes/' }
        ]
      }
    ],

    // 侧边栏
    sidebar: {
      '/posts/leetcode/': [
        {
          text: 'LeetCode 题解',
          collapsed: false,
          items: [
            { text: '分类索引', link: '/posts/leetcode/' },
            { text: 'Two Sum (两数之和)', link: '/posts/leetcode/two-sum' },
            { text: 'LRU Cache (LRU缓存)', link: '/posts/leetcode/lru-cache' },
            { text: '反转链表', link: '/posts/leetcode/reverse-linked-list' },
            { text: '最长回文子串', link: '/posts/leetcode/longest-palindromic-substring' }
          ]
        }
      ],
      '/posts/java/': [
        {
          text: 'Java 技术',
          collapsed: false,
          items: [
            { text: '分类索引', link: '/posts/java/' },
            { text: 'JVM 垃圾回收详解', link: '/posts/java/jvm-gc' },
            { text: 'HashMap 源码解析', link: '/posts/java/hashmap-source' }
          ]
        }
      ],
      '/posts/system-design/': [
        {
          text: '系统设计',
          collapsed: false,
          items: [
            { text: '分类索引', link: '/posts/system-design/' }
          ]
        }
      ],
      '/posts/interview/': [
        {
          text: '面试',
          collapsed: false,
          items: [
            { text: '分类索引', link: '/posts/interview/' }
          ]
        }
      ],
      '/posts/notes/': [
        {
          text: '随笔',
          collapsed: false,
          items: [
            { text: '分类索引', link: '/posts/notes/' }
          ]
        }
      ]
    },

    // 社交链接
    socialLinks: [
      { icon: 'github', link: 'https://github.com' }
    ],

    // 页脚
    footer: {
      message: '基于 VitePress 构建',
      copyright: 'Copyright © 2026'
    },

    // 上次更新时间
    lastUpdated: {
      text: '最后更新于',
      formatOptions: {
        dateStyle: 'short',
        timeStyle: 'short'
      }
    }
  }
})
