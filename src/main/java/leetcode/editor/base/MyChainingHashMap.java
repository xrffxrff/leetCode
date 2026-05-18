package leetcode.editor.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyChainingHashMap<K, V> {
    private static class KVNode<K,V> {
        K key;
        V value;
        KVNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private LinkedList<KVNode<K,V>>[] table;
    private static final int INIT_CAP = 4;

    public MyChainingHashMap(){
        this(INIT_CAP);
    }

    public MyChainingHashMap(int initCapacity) {
        size = 0;
        initCapacity = Math.max(1, initCapacity);
        table = (LinkedList<KVNode<K,V>>[]) new LinkedList[initCapacity];
        for (int i = 0; i<table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // **** 增/改 ****
    public void put(K key, V value){
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int hash = hash(key);
        LinkedList<KVNode<K,V>> list = table[hash];
        for (KVNode<K,V> kvNode : list) {
            if (key.equals(kvNode.key)) {
                kvNode.value = value;
                return;
            }
        }
        KVNode<K,V> newNode = new KVNode<>(key, value);
        list.add(newNode);
        size++;
        if (size >= table.length * 0.75) {
            resize(table.length * 2);
        }
    }

    public void remove(K key){
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int hash = hash(key);
        LinkedList<KVNode<K,V>> list = table[hash];
        for (KVNode<K,V> kvNode : list) {
            if (key.equals(kvNode.key)) {
                list.remove(kvNode);
                size--;

                // 当负载因子小于0.125时，缩容
                if (size <= table.length/8) {
                    resize(table.length / 4);
                }
            }
        }
    }

    public V get(K key){
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int hash = hash(key);
        LinkedList<KVNode<K,V>> list = table[hash];
        for (KVNode<K,V> kvNode : list) {
            if (key.equals(kvNode.key)) {
                return kvNode.value;
            }
        }
        return null;
    }

    //获取所有keys
    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for (LinkedList<KVNode<K,V>> list : table) {
            for (KVNode<K,V> kvNode : list) {
                keys.add(kvNode.key);
            }
        }
        return keys;
    }


    // 工具函数
    public int size(){
        return size;
    }

    public int hash(K key){
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public void resize(int newCap) {
        newCap = Math.max(newCap,1);
        MyChainingHashMap<K,V> newMap = new MyChainingHashMap<>(newCap);
        for (LinkedList<KVNode<K,V>> list : table) {
            for (KVNode<K,V> node : list) {
                newMap.put(node.key, node.value);
            }
        }
        this.table = newMap.table;
    }

    public static void main(String[] args) {
        MyChainingHashMap<Integer, Integer> map = new MyChainingHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println(map.get(1)); // 1
        System.out.println(map.get(2)); // 2

        map.put(1, 100);
        System.out.println(map.get(1)); // 100

        map.remove(2);
        System.out.println(map.get(2)); // null
        // [1, 3]（顺序可能不同）
        System.out.println(map.keys());

        map.remove(1);
        map.remove(2);
        map.remove(3);
        System.out.println(map.get(1)); // null
    }
}
