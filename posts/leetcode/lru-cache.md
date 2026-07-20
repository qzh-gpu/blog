---
title: "LRU Cache 缓存机制 — LeetCode 146"
date: 2026-07-19
tags: [LeetCode, 华为高频, 设计, 哈希表, 双向链表, 中等]
category: leetcode
difficulty: 中等
leetcodeId: 146
excerpt: "设计一个 LRU（最近最少使用）缓存机制。核心是 HashMap + 双向链表，get 和 put 操作均为 O(1) 时间复杂度。"
---

# LRU Cache 缓存机制

## 题目描述

设计并实现一个满足 **LRU (最近最少使用) 缓存** 约束的数据结构。

实现 `LRUCache` 类：
- `LRUCache(int capacity)` — 以正整数初始化缓存容量
- `int get(int key)` — 如果 key 存在则返回值，否则返回 -1
- `void put(int key, int value)` — 如果 key 存在则更新值，否则插入。当容量满时，**淘汰最久未使用的 key**

要求 `get` 和 `put` 均为 **O(1)** 时间复杂度。

---

## 数据结构设计

核心组合：**HashMap + 双向链表**

```
HashMap<Key, Node>  →  快速查找 O(1)
双向链表             →  维护访问顺序，头部=最近使用，尾部=最久未使用
```

```
  HEAD (dummy)                        TAIL (dummy)
     ⇄  [node1]  ⇄  [node2]  ⇄  [node3]  ⇄
       (最新)                          (最旧)
```

---

## Java 实现

```java
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    // 双向链表节点
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head, tail; // 哨兵节点

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);  // 最近使用 → 移到头部
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }
        // 新节点
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addToHead(newNode);

        if (cache.size() > capacity) {
            // 淘汰尾部（最久未使用）
            Node last = tail.prev;
            removeNode(last);
            cache.remove(last.key);
        }
    }

    // ---- 双向链表操作 ----
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}
```

---

## 复杂度分析

| 操作 | 时间复杂度 | 说明 |
|------|-----------|------|
| `get` | **O(1)** | HashMap 查找 + 链表移动 |
| `put` | **O(1)** | HashMap 插入 + 链表头插 + 尾删 |

---

## 扩展：使用 LinkedHashMap

Java 标准库的 `LinkedHashMap` 天然支持 LRU：

```java
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder=true
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
```

---

## 总结

- LRU Cache 是面试高频题，华为几乎必考
- 核心考点：**手写双向链表 + HashMap 组合**
- 实际工程中用 `LinkedHashMap` 即可，但面试需要能手写
