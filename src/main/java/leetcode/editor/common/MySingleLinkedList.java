package leetcode.editor.common;

import java.util.NoSuchElementException;

public class MySingleLinkedList<E> {
    private static class Node<E> {
        E val;
        Node<E> next;
        Node(E val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public MySingleLinkedList() {
        this.head = new Node<>(null);
        this.tail = head;
        this.size = 0;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head.next;
        head.next = newNode;
        if (size==0){
            tail = newNode;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void add(int index, E element){
        checkPositionIndex(index);

        if (index == size) {
            addLast(element);
        }

        Node<E> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<E> newNode = new Node<>(element);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> first = head.next;
        head.next = first.next;
        if (size == 1){
            tail = head;
        }
        size--;
        return first.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }
        E val = tail.val;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    public E remove(int index){
        checkElementIndex(index);

        Node<E> prev = head;
        for (int i = 0; i<index; i++){
            prev = prev.next;
        }

        Node<E> nodeToRemove = prev.next;
        prev.next = nodeToRemove.next;
        if (index == size - 1){
            tail = prev;
        }
        size--;
        return nodeToRemove.val;
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
        return tail.val;
    }

    public E get(int index){
        checkElementIndex(index);
        Node<E> p = getNode(index);
        return p.val;
    }

    public E set(int index, E element){
        checkElementIndex(index);
        Node<E> p =getNode(index);
        E oldVal = p.val;
        p.val = element;
        return oldVal;
    }

    // ***** 其他工具函数 *****
    public int getSize(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index){
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private Node<E> getNode(int index){
        Node<E> p = head.next;
        for(int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    public static void main(String[] args) {
        MySingleLinkedList<Integer> list = new MySingleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        list.add(2, 5);

        // 2,1,5,3,4
        for (int i = 0; i<list.size; i++){
            System.out.println(list.get(i));
        }

        System.out.println(list.removeFirst()); // 2
        System.out.println(list.removeLast()); // 4
        System.out.println(list.remove(1)); // 5

        System.out.println(list.getFirst()); // 1
        System.out.println(list.getLast()); // 3
        System.out.println(list.get(1)); // 3

    }
}
