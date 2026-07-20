import DefaultTheme from 'vitepress/theme'
import type { Theme } from 'vitepress'
import BlogList from './components/BlogList.vue'
import BlogPost from './components/BlogPost.vue'
import './style.css'

export default {
  extends: DefaultTheme,
  enhanceApp({ app }) {
    app.component('BlogList', BlogList)
    app.component('BlogPost', BlogPost)
  }
} satisfies Theme
