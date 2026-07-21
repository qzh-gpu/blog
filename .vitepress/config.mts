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
        {
          text: '华为高频 LeetCode', link: '/posts/leetcode/',
          items: [
            { text: '3. 无重复字符的最长子串', link: '/posts/leetcode/code3-longest-substring' },
            { text: '11. 盛最多水的容器', link: '/posts/leetcode/code11-container-with-most-water' },
            { text: '15. 三数之和', link: '/posts/leetcode/code15-3sum' },
            { text: '20. 有效的括号', link: '/posts/leetcode/code20-valid-parentheses' },
            { text: '23. 合并 K 个升序链表', link: '/posts/leetcode/code23-merge-k-sorted-lists' },
            { text: '42. 接雨水', link: '/posts/leetcode/code42-trapping-rain-water' },
            { text: '142. 环形链表 II', link: '/posts/leetcode/code142-linked-list-cycle-ii' },
            { text: '155. 最小栈', link: '/posts/leetcode/code155-min-stack' },
            { text: '200. 岛屿数量', link: '/posts/leetcode/code200-number-of-islands' },
            { text: '207. 课程表', link: '/posts/leetcode/code207-course-schedule' },
            { text: '209. 长度最小的子数组', link: '/posts/leetcode/code209-minimum-size-subarray-sum' },
            { text: '239. 滑动窗口最大值', link: '/posts/leetcode/code239-sliding-window-maximum' },
            { text: '394. 字符串解码', link: '/posts/leetcode/code394-decode-string' },
            { text: '695. 岛屿的最大面积', link: '/posts/leetcode/code695-max-area-of-island' },
            { text: '739. 每日温度', link: '/posts/leetcode/code739-daily-temperatures' }
          ]
        }
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
