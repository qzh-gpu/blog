# Blog Update Guide

本仓库是 VitePress 博客项目，工作目录是 `E:\Java\blog`。

线上地址：

```text
https://qzh-gpu.github.io/blog/
```

远程仓库：

```text
https://github.com/qzh-gpu/blog.git
```

本地开发地址通常是：

```text
http://localhost:5173/blog/
```

## 项目结构

```text
E:\Java\blog
├─ huawei\                 # 用户维护的 Java 原始文档/代码
├─ posts\leetcode\          # 博客中的 LeetCode Markdown 页面
├─ .vitepress\config.mts    # VitePress 配置、导航、侧边栏
├─ package.json             # npm 脚本
└─ .github\workflows\deploy.yml
```

## 重要原则

用户要求：`huawei` 目录里的内容怎么写，就怎么上传，不允许私自修改。

因此：

- 不要润色、改写、重排 `huawei/Code*.java` 的正文。
- 新博客页优先用 VitePress 的源码引用语法引用原文件，例如 `<<< @/huawei/Code1.java`。
- 只添加博客必须的 Markdown frontmatter、标题、入口链接和侧边栏配置。
- 不要把构建临时目录 `.vitepress/.temp/` 提交。
- 不要把无关的本地改动一起提交。

## 更新流程

1. 查看本地状态：

```powershell
git status --short
```

2. 查看 `huawei` 目录中新增或更新的文件：

```powershell
Get-ChildItem -Recurse -File huawei | Select-Object FullName,Length,LastWriteTime
```

3. 对每个新增的 `huawei/Code数字.java`，在 `posts/leetcode/` 下创建对应 Markdown 页面。

页面格式示例：

```markdown
---
title: "两数之和 — LeetCode 1"
date: 2026-07-29
tags: [哈希表, LeetCode, 华为高频, 简单]
category: leetcode
difficulty: 简单
leetcodeId: 1
excerpt: "两数之和。"
---

# 两数之和

## 代码实现

<<< @/huawei/Code1.java
```

4. 更新 `posts/leetcode/index.md`，保证新文章能在分类页看到。

常见方式是按 tag 分组：

```markdown
## 哈希表

<BlogList :posts="byTag('哈希表')" :showCategory="false" />
```

如果总题数变化，同步更新开头说明里的题目数量。

5. 更新 `.vitepress/config.mts` 的 `themeConfig.sidebar['/posts/leetcode/']`，把新文章加入左侧导航。

链接不要写 `.html` 后缀，例如：

```ts
{ text: '1. 两数之和', link: '/posts/leetcode/code1-two-sum' }
```

6. 构建验证。

PowerShell 可能禁止运行 `npm.ps1`，优先使用：

```powershell
npm.cmd run build
```

构建成功应看到类似：

```text
vitepress v1.6.4
build complete
```

7. 暂存需要上传的文件。

示例：

```powershell
git add .vitepress/config.mts posts/leetcode huawei
```

如果这份文档有更新，也加入：

```powershell
git add BLOG_UPDATE_GUIDE.md
```

8. 提交：

```powershell
git commit -m "Update Huawei LeetCode blog posts"
```

9. 推送：

```powershell
git push origin master
```

推送到 `master` 后，`.github/workflows/deploy.yml` 会自动构建并发布到 `gh-pages` 分支。

## 线上验证

部署后访问：

```text
https://qzh-gpu.github.io/blog/posts/leetcode/
```

也可以直接检查新文章，例如：

```text
https://qzh-gpu.github.io/blog/posts/leetcode/code1-two-sum.html
```

命令行验证：

```powershell
curl.exe -I https://qzh-gpu.github.io/blog/posts/leetcode/code1-two-sum.html
```

如果返回 `HTTP/1.1 200 OK`，说明页面已经发布。

如果浏览器刷新看不到更新：

- 等待 GitHub Pages 缓存刷新，通常几分钟。
- 用强制刷新：`Ctrl + F5`。
- 用无痕窗口打开。
- 直接打开新文章 URL，而不是只看首页。
- GitHub Pages 响应头里可能有 `Cache-Control: max-age=600`，代表最多缓存约 10 分钟。

## 常见注意事项

- 博客 base 配置是 `.vitepress/config.mts` 中的 `base: '/blog/'`，所以线上路径必须带 `/blog/`。
- 本地预览也要访问 `/blog/` 路径。
- 不要提交 `.vitepress/.temp/`、`.vitepress/dist/`、`node_modules/`。
- 如果 `git status` 里有用户无关改动，除非用户明确要求，不要回滚，也不要混进提交。
