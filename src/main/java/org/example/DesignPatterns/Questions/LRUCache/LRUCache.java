package org.example.DesignPatterns.Questions.LRUCache;

import java.util.HashMap;

public class LRUCache <K, V>{
    private int capacity;
    private HashMap<K, Node<K, V>> map;
    public DoublyLinkedList<K, V> list;

    LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public synchronized V get(K key) {
        if(!this.map.containsKey(key)) return null;
        Node node = map.get(key);
        this.list.moveToFront(node);
        return (V) node.value;
    }

    public synchronized void put(K key, V value) {
        // if key already exists.
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            this.list.remove(node);
            this.list.addFirst(node);
        } else {
            if(this.map.size() == this.capacity){
                Node node = this.list.removeLast();
                map.remove(node.key);
            }

            Node node = new Node(key, value);
            this.list.addFirst(node);
            this.map.put(key, node);
        }
    }
}
