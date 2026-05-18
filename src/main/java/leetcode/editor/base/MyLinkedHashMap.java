package leetcode.editor.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class MyLinkedHashMap<K,V> {
    private static class Node<K,V> {
        K key;
        V val;
        Node<K,V> prev, next;
        Node (K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private final Node<K,V> head, tail;
    private final HashMap<K,Node<K,V>> map = new HashMap<>();

    public MyLinkedHashMap(){
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key){
        Node<K,V> node = map.get(key);
        if (node!=null) {
            return node.val;
        }
        return null;
    }

    public void put(K key, V val) {
        if (!map.containsKey(key)) {
            // 若为新插入的节点，则同时插入链表和map
            Node<K,V> node = new Node<>(key, val);
            map.put(key, node);
            addLastNode(node);
        }
        map.get(key).val = val;
    }

    public void remove(K key) {
        if (!map.containsKey(key)) {
            return;
        }

        Node<K,V> node = map.remove(key);
        removeNode(node);
    }

    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for (Node<K,V> p = head.next; p!=tail; p = p.next){
            keys.add(p.key);
        }
        return keys;
    }

    private void addLastNode(Node<K,V> x){
        Node<K,V> temp = tail.prev;
        x.next = tail;
        tail.prev = x;

        x.prev = temp;
        temp.next = x;
    }

    private void removeNode(Node<K,V> x){
        Node<K,V> prev = x.prev;
        Node<K,V> next = x.next;
        prev.next = next;
        next.prev = prev;
        x.next = x.prev = null;
    }

    public int size (){
        return map.size();
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public static void main(String[] args) {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        System.out.println(map.keys()); // [a, b, c, d, e]
        map.remove("c");
        System.out.println(map.keys()); // [a, b, d, e]
    }

}
