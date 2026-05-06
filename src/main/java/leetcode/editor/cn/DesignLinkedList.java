
package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import javax.xml.soap.Node;
import java.util.NoSuchElementException;

public class DesignLinkedList {

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyLinkedList {
        private class Node {
            int val;
            Node prev;
            Node next;
            Node(int val) {
                this.val = val;
            }
        }

        private Node head;
        private Node tail;
        private int size;

        public MyLinkedList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        public int get(int index) {
            if (index<0 || index>=size) {
                return -1;
            }

            Node cur = head.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }
        
        public void addAtHead(int val) {
            Node p = head.next;
            Node newNode = new Node(val);
            newNode.next = p;
            p.prev = newNode;
            newNode.prev = head;
            head.next = newNode;
            size++;
        }
        
        public void addAtTail(int val) {
            Node p = tail.prev;
            Node newNode = new Node(val);
            p.next = newNode;
            newNode.prev = p;
            newNode.next = tail;
            tail.prev = newNode;
            size++;
        }
        
        public void addAtIndex(int index, int val) {
            if (index==size) {
                addAtTail(val);
                return;
            }
            if (index <0 || index>size) {
                return;
            }
            Node p = head.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            Node prev = p.prev;
            Node newNode = new Node(val);
            newNode.next = p;
            p.prev = newNode;
            newNode.prev = prev;
            prev.next = newNode;
            size++;
        }
        
        public void deleteAtIndex(int index) {
            if (index<0 || index>=size) {
                return;
            }
            Node p = head.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            Node prev = p.prev;
            Node next = p.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public void printList(){
            Node cur = head.next;
            while (cur != tail) {
                System.out.print(cur.val + "->");
                cur = cur.next;
            }
            System.out.println("\n");
            System.out.println("-------------");
        }
    }
    
    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    //leetcode submit region end(Prohibit modification and deletion)

    
//    public static void main(String[] args) {
//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(1);
//        myLinkedList.printList();
//        myLinkedList.addAtTail(3);
//        myLinkedList.printList();
//        myLinkedList.addAtIndex(1, 2); // 链表变为 1->2->3
//        myLinkedList.printList();
//        myLinkedList.get(1); // 返回 2
//        int x = myLinkedList.get(1);
//        System.out.println(x);
//        myLinkedList.deleteAtIndex(1); // 现在，链表变为 1->3
//        myLinkedList.printList();
//        myLinkedList.get(1); // 返回 3
//        int y = myLinkedList.get(1);
//        System.out.println(y);
//    }
}