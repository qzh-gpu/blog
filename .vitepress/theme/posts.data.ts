import { createContentLoader } from 'vitepress'

export interface Post {
  title: string
  url: string
  date: string
  tags: string[]
  category: string
  excerpt: string
  difficulty?: string
  leetcodeId?: number
}

export default createContentLoader('posts/**/*.md', {
  excerpt: true,
  transform(rawData): Post[] {
    return rawData
      .filter(page => page.url !== '/posts/' && !page.url.endsWith('/posts/'))
      .map(({ url, frontmatter, excerpt }) => ({
        title:      frontmatter.title || 'Untitled',
        url,
        date:       frontmatter.date || '1970-01-01',
        tags:       frontmatter.tags || [],
        category:   frontmatter.category || '',
        excerpt:    frontmatter.excerpt || excerpt || '',
        difficulty: frontmatter.difficulty,
        leetcodeId: frontmatter.leetcodeId
      }))
      .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
  }
})
