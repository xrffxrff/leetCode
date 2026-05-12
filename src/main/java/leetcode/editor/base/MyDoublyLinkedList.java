package leetcode.editor.base;

import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> {
    private static class Node<E> {
        E val;
        Node<E> prev;
        Node<E> next;
        Node(E val) {
            this.val = val;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyDoublyLinkedList(){
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.size = 0;
        head.next = tail;
        tail.prev = head;
    }

    // **** 增 *****
    public void addFirst(E val){
        Node<E> newNode = new Node(val);
        Node<E> temp = head.next;

        temp.prev = newNode;
        newNode.next = temp;

        newNode.prev = head;
        head.next = newNode;

        size++;
    }

    public void addLast(E val){
        Node<E> newNode = new Node<>(val);
        Node<E> temp = tail.prev;

        temp.next = newNode;
        newNode.prev = temp;

        newNode.next = tail;
        tail.prev = newNode;
        size++;
    }

    public void add(E val, int index){
        checkPositionIndex(index);
        if (index == size){
            addLast(val);
            return;
        }

        Node<E> p = getNode(index);
        Node<E> temp = p.prev;
        Node<E> newNode = new Node<>(val);

        newNode.next = p;
        p.prev = newNode;

        newNode.prev = temp;
        temp.next = newNode;

        size++;
    }

    // **** 删 *****
    public E removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        Node<E> x = head.next;
        Node<E> temp = x.next;
        head.next = temp;
        temp.prev = head;

        E val = x.val;
        x.prev = null;
        x.next = null;

        size--;

        return val;
    }

    private E removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        Node<E> x = tail.prev;
        Node<E> temp = x.prev;
        tail.prev = temp;
        temp.next = tail;

        E val = x.val;
        x.prev = null;
        x.next = null;

        size--;

        return val;
    }

    private E remove(int index){
        checkElementIndex(index);

        Node<E> x = getNode(index);
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        prev.next = next;
        next.prev = prev;

        E val = x.val;
        x.next = null;
        x.prev = null;

        size--;

        return val;

    }

    // **** 查 *****
    public E get(int index){
        checkElementIndex(index);
        Node<E> x = getNode(index);
        return x.val;
    }

    public E getFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    // **** 改 *****
    public E set(int index, E val){
        checkElementIndex(index);
        Node<E> x = getNode(index);
        E oldVal = x.val;
        x.val = val;
        return oldVal;
    }

    // **** 其他工具函数 *****
    private boolean isEmpty(){
        return size == 0;
    }

    private Node<E> getNode(int index){
        checkElementIndex(index);

        Node<E> temp = head.next;
        // 循环 index 次，移动到目标节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index){
        return index >=0 && index <= size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index){
        if (!isElementIndex(index)){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index){
        if (!isPositionIndex(index)){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
