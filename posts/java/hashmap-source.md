---
title: "HashMap 源码解析"
date: 2026-07-14
tags: [Java, 集合, HashMap, 源码, 面试]
category: java
excerpt: "深入分析 JDK 8 HashMap 的底层实现：数组+链表+红黑树、hash 扰动函数、扩容机制、线程安全问题。"
---

# HashMap 源码解析

## 底层数据结构

JDK 8 的 HashMap：**数组 + 链表 + 红黑树**

```
数组 (Node<K,V>[] table)
  │
  ├─ [0] → Node → Node → ...          (链表，长度 <8)
  ├─ [1] → TreeNode ⇄ TreeNode ⇄ ...  (红黑树，长度 ≥8 且 table ≥64)
  ├─ [2] → null
  ├─ ...
  └─ [n] → Node
```

---

## 核心源码分析

### 1. 哈希扰动函数

```java
static final int hash(Object key) {
    int h;
    // 高 16 位与低 16 位异或，增加低位的随机性
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

**为什么这样设计？**
- 计算数组下标时用 `(n-1) & hash`（等价于取模），只用到了低位
- 高 16 位异或到低 16 位，让高位也参与下标计算，减少碰撞

### 2. put 方法

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 1. 数组为空 → 扩容（懒加载）
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 2. 槽位为空 → 直接插入
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        // 3. 槽位非空 → 处理冲突
        Node<K,V> e; K k;
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            e = p; // key 相同，更新
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 链表长度 ≥ 8 → 转红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1)
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // key 已存在，更新值
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            return oldValue;
        }
    }
    // 4. 超过阈值 → 扩容
    if (++size > threshold)
        resize();
    return null;
}
```

### 3. 扩容机制

```java
// 每次扩容为原来的 2 倍
// 扩容后重新计算下标：要么在原位置，要么在原位置 + 旧容量
newIndex = oldIndex 或 oldIndex + oldCapacity
```

---

## 关键常量

| 常量 | 默认值 | 说明 |
|------|--------|------|
| `DEFAULT_INITIAL_CAPACITY` | 16 | 初始容量 |
| `DEFAULT_LOAD_FACTOR` | 0.75 | 负载因子 |
| `TREEIFY_THRESHOLD` | 8 | 链表转红黑树阈值 |
| `UNTREEIFY_THRESHOLD` | 6 | 红黑树退化为链表阈值 |
| `MIN_TREEIFY_CAPACITY` | 64 | 树化所需的最小 table 容量 |

---

## 面试重点

### 为什么负载因子是 0.75？
- **空间 vs 时间** 的折中
- 太高 → 碰撞增加，查询变慢
- 太低 → 频繁扩容，浪费空间
- 0.75 是泊松分布推导的经验最优值

### 为什么线程不安全？
- 多线程 put 可能导致**数据覆盖**
- JDK 7 中扩容时可能形成**死循环**（头插法）
- JDK 8 改为尾插法，但仍有数据不一致问题
- 多线程环境用 **ConcurrentHashMap**

---

## 总结

- HashMap 的核心是 **hash + 数组 + 链表/红黑树**
- JDK 8 的树化优化解决了 hash 碰撞攻击问题
- 面试务必能说清 put 流程和扩容机制
