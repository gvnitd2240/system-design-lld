package org.example.DesignPatterns.Questions.LRUCache;

public class DoublyLinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    DoublyLinkedList(){
        // TODO: Create dummy head and tail nodes with null key/value
        // TODO: Link head.next to tail and tail.prev to head
        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        // TODO: Insert node right after head
        // Steps:
        // 1. node.next = head.next
        // 2. node.prev = head
        // 3. head.next.prev = node
        // 4. head.next = node
    }

    public void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void moveToFront(Node<K, V> node) {
        remove(node);
        addFirst(node);
    }

    public Node<K, V> removeLast() {
        if(tail.prev == head) return null;

        Node<K, V> node = tail.prev;
        remove(node);
        return node;
    }
}
