package leetcode.editor.base;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyArrayList<E> {
    private E[] data;
    private int size;
    // 默认初始容量
    private static final int INIT_CAP = 1;

    public MyArrayList(){
        this(INIT_CAP);
    }

    public MyArrayList(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public void addFirst(E e){
        if (size == data.length){
            resize(2 * data.length);
        }
        for (int i = size; i > 0; i--){
            data[i] = data[i-1];
        }
        data[0] = e;
        size++;
    }

    public void addLast(E e){
        if (size == data.length){
            resize(2 * data.length);
        }
        data[size] = e;
        size++;
    }

    public void add(int index, E e){
        checkPositionIndex(index);
        if (size == data.length){
            resize(2 * data.length);
        }
        if (index == size){
            addLast(e);
            return;
        }
        if (index == 0){
            addFirst(e);
            return;
        }
        for (int i = size; i > index; i--){
            data[i] = data[i-1];
        }
        data[index] = e;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int cap = data.length;
        // 可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E first = data[0];
        for (int i = 0; i < size-1; i++){
            data[i] = data[i+1];
        }
        size--;
        return first;
    }

    public E removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int cap = data.length;
        // 可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E last = data[size-1];
        data[size-1] = null;
        size--;
        return last;
    }

    public E remove(int index){
        checkElementIndex(index);
        int cap = data.length;
        // 可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E e = data[index];
        for (int i = index; i < size-1; i++){
            data[i] = data[i+1];
        }
        data[size-1] = null;
        size--;
        return e;
    }


    public E get(int index){
        checkElementIndex(index);
        return data[index];
    }

    public E set(int index, E e){
        checkElementIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    public int size(){
        return size;
    }


    /**
     * 重置数组容量
     * @param capacity 新容量
     */
    private void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++){
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }


    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.addFirst(1);
        list.addLast(2);
        list.add(1,3);
        for (int i = 0;i< list.size;i++) {
            System.out.println(list.get(i));
        }
    }
}
