package huawei;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    // 双向链表节点定义
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;  // key -> 链表节点
    private Node head;               // 哑头节点，head.next 是最近使用的节点
    private Node tail;               // 哑尾节点，tail.prev 是最久未使用的节点

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // 初始化哑节点
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        // 访问后，将该节点移到链表头部（标记为最近使用）
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // key 已存在：更新值，移到头部
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // key 不存在：需要插入新节点
            if (map.size() == capacity) {
                // 缓存已满，淘汰最久未使用的节点（即尾部节点）
                Node toRemove = tail.prev;
                removeNode(toRemove);
                map.remove(toRemove.key);
            }
            // 创建新节点，添加到头部
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    // ========== 链表辅助操作 ==========

    // 将节点添加到头部（head 之后）
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 从链表中移除一个节点
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 将节点移动到头部（先删除，再添加到头部）
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // 移除尾部节点（tail.prev），即最久未使用的节点
    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
