package datastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public class LRUCache {

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
    }

    public int get(int key) {
        // System.out.println(key);
        // System.out.println(map);
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            add(n);
            return n.value;
        }
        // System.out.println("map");
        return -1;
    }

    public void put(int key, int value) {
        Node newNode = new Node(value);
        newNode.key = key;
        Node n = map.get(key);
        if (key == 4) System.out.println(map);
        if (n != null) {
            // if key exists update value
            map.put(key, newNode);
            // update order
            remove(n);
            add(newNode);
            return;
        } else if (map.keySet().size() >= this.capacity) {
            // If exceeds capacity remove least used]
            Node toRemove = tail.previous;
            remove(toRemove);
            map.remove(toRemove.key);
        }

        add(newNode);
        map.put(key, newNode);
    }

    private void remove(Node node) {
        Node prev = node.previous;
        Node next = node.next;

        prev.next = next;
        next.previous = prev;
    }

    private void add(Node node) {
        Node h1 = head.next;
        head.next = node;
        node.previous = head;
        node.next = h1;
        h1.previous = node;
    }

    class Node {
        Node next;
        Node previous;
        int value;
        int key;

        Node (int value) {
            this.value = value;
        }

        Node (int value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
