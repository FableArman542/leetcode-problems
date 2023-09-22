package datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheTest {

    public static void main(String[] args) {
        CacheTest cacheTest = new CacheTest(2);

        cacheTest.printCache();
        cacheTest.put(1, 1);
        cacheTest.put(2, 2);
        cacheTest.printCache();
        cacheTest.put(3, 3);
        cacheTest.printCache();
        cacheTest.read(2);
        cacheTest.printCache();
        cacheTest.put(3, 53);
        cacheTest.printCache();
    }

    private Node head, tail;
    private int capacity;
    private Map<Integer, Node> storedKeys;

    public CacheTest(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.storedKeys = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void printCache() {
        Node h = head.next;
        while (h != tail) {
            System.out.println("[" + h.key + ", " + h.value + "]");
            h = h.next;
        }
        System.out.println("-----");
    }

    private void addToFrontQueue(Node node) {
        Node pNext = head.next;
        head.next = node;
        node.next = pNext;
        node.prev = head;
        pNext.prev = node;
    }

    private void removeFromQueue(Node node) {
        Node nPrev = node.prev;
        Node nNext = node.next;
        nPrev.next = nNext;
        nNext.prev = nPrev;
    }

    public int read(int key) {
        return Optional.ofNullable(storedKeys.get(key))
                .map(node -> {
                    removeFromQueue(node);
                    addToFrontQueue(node);
                    return node.value;
                }).orElse(-1);
    }

    public void put(int key, int value) {
        Node node = storedKeys.get(key);
        if (node != null) {
            node.value = value;
            removeFromQueue(node);
            addToFrontQueue(node);
        } else {
            Node newNode = new Node(key, value);
            if (storedKeys.values().size() >= capacity) {
                // remove LRU node
                Node tailPrev = tail.prev;
                removeFromQueue(tailPrev);
                storedKeys.remove(tailPrev.key);
            }

            addToFrontQueue(newNode);
            storedKeys.put(key, newNode);
        }
    }

    class Node {
        Node prev, next;
        int key, value;

        public Node() {

        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
