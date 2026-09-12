import { defineConfig } from 'vitepress'

export default defineConfig({
  title: '技术与面试笔记',
  description: '华为高频 LeetCode · CodeTop · RAG 场景题 · Agent 场景题',
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
          { text: 'CodeTop', link: '/posts/leetcode/codetop/' },
          { text: 'RAG 场景题', link: '/posts/rag/' },
          { text: 'Agent 场景题', link: '/posts/agent/' }
        ]
      }
    ],

    sidebar: {
      '/posts/leetcode/': [
        { text: '分类概览', link: '/posts/leetcode/' },
        {
          text: 'CodeTop',
          collapsed: false,
          items: [
            { text: '4. 寻找两个正序数组的中位数（二分查找）', link: '/posts/leetcode/codetop/code4-median-of-two-sorted-arrays' },
            { text: '5. 最长回文子串（字符串 / 双指针）', link: '/posts/leetcode/codetop/code5-longest-palindromic-substring' },
            { text: '25. K 个一组翻转链表（链表）', link: '/posts/leetcode/codetop/code25-reverse-nodes-in-k-group' },
            { text: '33. 搜索旋转排序数组（二分查找）', link: '/posts/leetcode/codetop/code33-search-in-rotated-sorted-array' },
            { text: '82. 删除排序链表中的重复元素 II（链表 / 双指针）', link: '/posts/leetcode/codetop/code82-remove-duplicates-from-sorted-list-ii' },
            { text: '88. 合并两个有序数组（数组 / 双指针）', link: '/posts/leetcode/codetop/code88-merge-sorted-array' },
            { text: '103. 二叉树的锯齿形层序遍历（二叉树 / BFS）', link: '/posts/leetcode/codetop/code103-binary-tree-zigzag-level-order-traversal' },
            { text: '121. 买卖股票的最佳时机（贪心）', link: '/posts/leetcode/codetop/code121-best-time-to-buy-and-sell-stock' },
            { text: '143. 重排链表（链表 / 快慢指针 / 反转链表）', link: '/posts/leetcode/codetop/code143-reorder-list' },
            { text: '160. 相交链表（链表 / 双指针）', link: '/posts/leetcode/codetop/code160-intersection-of-two-linked-lists' },
            { text: '165. 比较版本号（字符串 / 双指针）', link: '/posts/leetcode/codetop/code165-compare-version-numbers' },
            { text: '199. 二叉树的右视图（二叉树 / BFS）', link: '/posts/leetcode/codetop/code199-binary-tree-right-side-view' },
            { text: '236. 二叉树的最近公共祖先（二叉树 / 递归 / DFS）', link: '/posts/leetcode/codetop/code236-lowest-common-ancestor-of-a-binary-tree' },
            { text: '415. 字符串相加（字符串 / 模拟）', link: '/posts/leetcode/codetop/code415-add-strings' },
            { text: '912. 排序数组（排序 / 快速排序 / 递归）', link: '/posts/leetcode/codetop/code912-sort-an-array' },
            { text: '1143. 最长公共子序列（动态规划）', link: '/posts/leetcode/codetop/code1143-longest-common-subsequence' }
          ]
        },
        {
          text: '双指针',
          collapsed: false,
          items: [
            { text: '11. 盛最多水的容器', link: '/posts/leetcode/code11-container-with-most-water' },
            { text: '15. 三数之和', link: '/posts/leetcode/code15-3sum' },
            { text: '19. 删除链表的倒数第 N 个结点', link: '/posts/leetcode/code19-remove-nth-node-from-end-of-list' },
            { text: '42. 接雨水', link: '/posts/leetcode/code42-trapping-rain-water' },
            { text: '209. 长度最小的子数组', link: '/posts/leetcode/code209-minimum-size-subarray-sum' }
          ]
        },
        {
          text: '链表',
          collapsed: true,
          items: [
            { text: '2. 两数相加', link: '/posts/leetcode/code2-add-two-numbers' },
            { text: '19. 删除链表的倒数第 N 个结点', link: '/posts/leetcode/code19-remove-nth-node-from-end-of-list' },
            { text: '21. 合并两个有序链表', link: '/posts/leetcode/code21-merge-two-sorted-lists' },
            { text: '92. 反转链表 II', link: '/posts/leetcode/code92-reverse-linked-list-ii' },
            { text: '142. 环形链表 II', link: '/posts/leetcode/code142-linked-list-cycle-ii' },
            { text: '146. LRU 缓存', link: '/posts/leetcode/code146-lru-cache' },
            { text: '206. 反转链表', link: '/posts/leetcode/code206-reverse-linked-list' }
          ]
        },
        {
          text: '哈希表',
          collapsed: true,
          items: [
            { text: '1. 两数之和', link: '/posts/leetcode/code1-two-sum' },
            { text: '146. LRU 缓存', link: '/posts/leetcode/code146-lru-cache' },
            { text: '451. 根据字符出现频率排序', link: '/posts/leetcode/code451-sort-characters-by-frequency' }
          ]
        },
        {
          text: '滑动窗口',
          collapsed: true,
          items: [
            { text: '3. 无重复字符的最长子串', link: '/posts/leetcode/code3-longest-substring' },
            { text: '239. 滑动窗口最大值', link: '/posts/leetcode/code239-sliding-window-maximum' }
          ]
        },
        {
          text: '栈 / 单调栈',
          collapsed: true,
          items: [
            { text: '20. 有效的括号', link: '/posts/leetcode/code20-valid-parentheses' },
            { text: '155. 最小栈', link: '/posts/leetcode/code155-min-stack' },
            { text: '394. 字符串解码', link: '/posts/leetcode/code394-decode-string' },
            { text: '739. 每日温度', link: '/posts/leetcode/code739-daily-temperatures' }
          ]
        },
        {
          text: 'DFS / 图',
          collapsed: true,
          items: [
            { text: '200. 岛屿数量', link: '/posts/leetcode/code200-number-of-islands' },
            { text: '207. 课程表', link: '/posts/leetcode/code207-course-schedule' },
            { text: '695. 岛屿的最大面积', link: '/posts/leetcode/code695-max-area-of-island' }
          ]
        },
        {
          text: '二叉树',
          collapsed: true,
          items: [
            { text: '102. 二叉树的层序遍历', link: '/posts/leetcode/code102-binary-tree-level-order-traversal' },
            { text: '124. 二叉树中的最大路径和', link: '/posts/leetcode/code124-binary-tree-maximum-path-sum' }
          ]
        },
        {
          text: '动态规划',
          collapsed: true,
          items: [
            { text: '53. 最大子数组和', link: '/posts/leetcode/code53-maximum-subarray' },
            { text: '64. 最小路径和', link: '/posts/leetcode/code64-minimum-path-sum' },
            { text: '70. 爬楼梯', link: '/posts/leetcode/code70-climbing-stairs' },
            { text: '72. 编辑距离', link: '/posts/leetcode/code72-edit-distance' },
            { text: '300. 最长递增子序列', link: '/posts/leetcode/code300-longest-increasing-subsequence' }
          ]
        },
        {
          text: '回溯',
          collapsed: true,
          items: [
            { text: '46. 全排列', link: '/posts/leetcode/code46-permutations' },
            { text: '93. 复原 IP 地址', link: '/posts/leetcode/code93-restore-ip-addresses' }
          ]
        },
        {
          text: '贪心',
          collapsed: true,
          items: [
            { text: '55. 跳跃游戏', link: '/posts/leetcode/code55-jump-game' },
            { text: '406. 根据身高重建队列', link: '/posts/leetcode/code406-queue-reconstruction-by-height' }
          ]
        },
        {
          text: '数组 / 排序',
          collapsed: true,
          items: [
            { text: '54. 螺旋矩阵', link: '/posts/leetcode/code54-spiral-matrix' },
            { text: '56. 合并区间', link: '/posts/leetcode/code56-merge-intervals' },
            { text: '215. 数组中的第 K 个最大元素', link: '/posts/leetcode/code215-kth-largest-element-in-an-array' },
            { text: '406. 根据身高重建队列', link: '/posts/leetcode/code406-queue-reconstruction-by-height' },
            { text: '451. 根据字符出现频率排序', link: '/posts/leetcode/code451-sort-characters-by-frequency' }
          ]
        },
        {
          text: '二分查找',
          collapsed: true,
          items: [
            { text: '704. 二分查找', link: '/posts/leetcode/code704-binary-search' }
          ]
        },
        {
          text: '堆 / 优先队列',
          collapsed: true,
          items: [
            { text: '23. 合并 K 个升序链表', link: '/posts/leetcode/code23-merge-k-sorted-lists' }
          ]
        },
        {
          text: '快慢指针',
          collapsed: true,
          items: [
            { text: '142. 环形链表 II', link: '/posts/leetcode/code142-linked-list-cycle-ii' }
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
