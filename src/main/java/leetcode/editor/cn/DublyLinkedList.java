package leetcode.editor.cn;

public class DublyLinkedList {
    static class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode prev, ListNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 数组转为双向链表
     */
    ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur.next.prev = cur;
            cur = cur.next;
        }

        return head;
    }


    public static void main(String[] args) {
        DublyLinkedList dublyLinkedList = new DublyLinkedList();
        ListNode head = dublyLinkedList.createLinkedList(new int[]{1,2,3,4,5});

        // 从头节点开始向后遍历
        ListNode tail = null;
        for (ListNode p = head; p != null; p = p.next) {
            System.out.println(p.val);
            tail = p;
        }
        System.out.println("----------------------");

        // 从尾节点开始向前遍历
        for (ListNode p = tail; p != null; p = p.prev) {
            System.out.println(p.val);
        }
        System.out.println("----------------------");
    }


}
