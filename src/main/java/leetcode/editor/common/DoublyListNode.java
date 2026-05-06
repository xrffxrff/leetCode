package leetcode.editor.common;

public class DoublyListNode<E> {
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

    public DoublyListNode(){
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.size = 0;
        head.next = tail;
        tail.prev = head;
    }

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

    public void add(E val){

    }
}
