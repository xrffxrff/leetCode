package leetcode.editor.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyArrayHashMap<K,V> {
    private static class Node<K,V> {
        K key;
        V val;
        Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    // 存储key和key在array中的索引
    private final HashMap<K, Integer> map = new HashMap<>();
    // 真正存储key-value的数组
    private final ArrayList<Node<K,V>> arr = new ArrayList<>();

    private final Random r = new Random();

    // 增或者改
    public void put(K key, V val){
        if (containsKey(key)) {
            int index = map.get(key);
            arr.get(index).val = val;
            return;
        }
        arr.add(new Node<>(key, val));
        map.put(key, arr.size()-1);
    }

    public V get(K key) {
        if (containsKey(key)) {
            Integer index = map.get(key);
            return arr.get(index).val;
        }
        return null;
    }

    public void remove(K key){
        if (!containsKey(key)) {
            return;
        }
        int index = map.get(key);
        Node<K,V> node = arr.get(index);

        // 最后一个元素e和第index个元素node互换位置
        Node<K,V> e = arr.get(size()-1);
        arr.set(index, e);
        arr.set(size()-1, node);

        // 修改map中e.key对应的索引
        map.put(e.key, index);

        // arr中移出元素
        arr.remove(arr.size()-1);

        // map中移出元素
        map.remove(node.key);
    }

    // 随机弹出一个键
    public K randomKey(){
        int index = r.nextInt(arr.size());
        return arr.get(index).key;
    }

    public int size(){
        return map.size();
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public static void main(String[] args) {
        MyArrayHashMap <Integer, Integer> map = new MyArrayHashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);

        System.out.println(map.get(2));
        System.out.println(map.get(4));

        System.out.println(map.randomKey());
        System.out.println(map.randomKey());
        System.out.println(map.randomKey());

        map.remove(4);
        System.out.println(map.randomKey());

    }
}
