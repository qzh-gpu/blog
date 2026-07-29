---
title: "课程表 — LeetCode 207"
date: 2026-07-21
tags: [DFS, LeetCode, 华为高频, DFS, 图, 拓扑排序, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 207
excerpt: "判断有向图是否存在环，即是否能完成所有课程。使用 DFS 三色标记法检测环。"
---

# 课程表

## 题目描述

你这个学期必须选修 `numCourses` 门课程。先修课程按数组 `prerequisites` 给出，其中 `prerequisites[i] = [a, b]` 表示如果要学习课程 `a` 则必须先学习课程 `b`。请你判断是否可能完成所有课程。

## 示例

```
输入: numCourses = 2, prerequisites = [[1,0]]
输出: true
解释: 先修0再修1，可以完成。

输入: numCourses = 2, prerequisites = [[1,0],[0,1]]
输出: false
解释: 两门课互相依赖，形成死循环。
```

## 思路分析

> 课程表：题目的是判断一个有向图是否存在环。每门课程是一个节点，先修关系 [a, b] 表示有一条从 b 指向 a 的有向边，如果图中存在环，则无法完成所有课程，否则可以。

> **DFS（深度优先搜索）**——对每个节点进行状态标记（未访问/访问中/已访问），若在 DFS 过程中遇到"访问中"的节点，说明存在环。

> **数据结构选型**：使用邻接表 `List<List<Integer>>` graph 存储图，graph[i] 存储课程 i 的所有后继课程。使用 int[] state 记录每个节点的访问状态：0 = 未访问，1 = 访问中（当前 DFS 路径上），2 = 已访问完成（无环）。若在 DFS 过程中访问到状态为 1 的节点，则存在环。

## 代码实现

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    // 课程表：题目的是判断一个有向图是否存在环。每门课程是一个节点，先修关系[a,b]表示有一条从b指向a的有向边，
    // 如果图中存在环，则无法完成所有课程，否则可以

    // DFS（深度优先搜索） —— 对每个节点进行状态标记（未访问/访问中/已访问），若在 DFS 过程中遇到"访问中"的节点，说明存在环。
    /*
    数据结构选型
    使用邻接表 List<List<Integer>> graph 存储图，graph[i] 存储课程 i 的所有后继课程。

    使用 int[] state 记录每个节点的访问状态：

    0：未访问

    1：访问中（当前 DFS 路径上）

    2：已访问完成（无环）

    若在 DFS 过程中访问到状态为 1 的节点，则存在环。

    算法流程
    构建邻接表。

    对每个节点 i，若状态为 0，则调用 dfs(i)。

    dfs(i) 将 state[i] 设为 1，遍历所有后继 j：

    若 state[j] == 1，说明遇到环，返回 false。

    若 state[j] == 0，递归调用 dfs(j)，若返回 false，则传播 false。

    遍历完后将 state[i] 设为 2，返回 true。

    所有节点均通过则返回 true。
     */
    /**
     * 判断是否能完成所有课程（即判断有向图是否存在环）
     * @param numCourses  课程总数（节点数）
     * @param prerequisites 先修关系数组，每个元素 [a, b] 表示学 a 必须先学 b（即 b -> a 的有向边）
     * @return true 表示可以完成所有课程，false 表示存在环，无法完成
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // ============ 第 1 步：构建邻接表 ============
        // 创建一个 List，里面装着 numCourses 个 ArrayList，每个 ArrayList 代表一个课程指向的所有后继课程
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>()); // 给每门课都配一个空的"后继名单"
        }

        // 遍历所有先修关系，填入邻接表
        // 例如 prerequisites = [[1, 0]]，表示 0 -> 1
        // 那么 graph.get(0).add(1)，即在课程 0 的名单里加入 1
        for (int[] pre : prerequisites) {
            int course = pre[0];      // 后修课（目标节点）
            int prereq = pre[1];      // 先修课（起点节点）
            graph.get(prereq).add(course); // 从先修课指向后修课
        }

        // ============ 第 2 步：准备状态数组 ============
        // state[i] 表示课程 i 的访问状态
        // 0 = 还未被访问过（未开始）
        // 1 = 正在当前 DFS 路径中（访问中，若再次遇到说明有环）
        // 2 = 已经完成所有后续遍历，确认无环（安全）
        int[] state = new int[numCourses];

        // ============ 第 3 步：从每门课开始触发 DFS ============
        // 因为图可能不连通，需要确保每个节点都被检查到
        for (int i = 0; i < numCourses; i++) {
            // 如果这门课还没被访问过，就以它为起点开始 DFS
            if (state[i] == 0) {
                // 如果 DFS 返回 false，说明发现了环，直接返回 false
                if (!dfs(graph, state, i)) {
                    return false;
                }
            }
        }

        // 所有课程都遍历完了，没有发现环，可以完成所有课程
        return true;
    }

    /**
     * 深度优先搜索，检测从当前节点出发是否存在环
     * @param graph 邻接表（地图）
     * @param state 状态数组（记录本）
     * @param cur   当前正在访问的课程编号
     * @return true 表示从当前节点出发无环，false 表示有环
     */
    private boolean dfs(List<List<Integer>> graph, int[] state, int cur) {
        // ============ 第 1 步：标记当前节点为"访问中" ============
        state[cur] = 1;
        // 相当于在门牌上挂一个牌子："我正在这间教室里，别绕回来找我"

        // ============ 第 2 步：遍历当前节点的所有后继课程 ============
        for (int next : graph.get(cur)) {
            // 情况 1：如果后继节点状态也是 1（访问中），说明绕回来了，发现环！
            if (state[next] == 1) {
                return false; // 有环，直接返回 false
            }

            // 情况 2：如果后继节点状态是 0（未访问），递归进去检查
            if (state[next] == 0) {
                // 如果递归返回 false，说明下一层发现了环，立即向上传递 false
                if (!dfs(graph, state, next)) {
                    return false;
                }
            }

            // 情况 3：如果后继节点状态是 2（已完成），说明它已经确认无环了
            // 直接跳过，不用重复检查（类似备忘录的作用）
        }

        // ============ 第 3 步：当前节点所有后继都检查完了，没问题 ============
        state[cur] = 2; // 标记为"已完成"，以后别人遇到它可以直接跳过
        return true;    // 从当前节点出发无环，安全返回
    }
}
```

## 复杂度分析

| 复杂度 | |
|--------|--------|
| 时间 | **O(N + E)** — N 为节点数（课程），E 为边数（先修关系） |
| 空间 | **O(N + E)** — 邻接表 + 递归栈 |
