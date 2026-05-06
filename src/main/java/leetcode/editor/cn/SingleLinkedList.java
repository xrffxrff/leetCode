package leetcode.editor.cn;

public class SingleLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 数组转为链表
     * @param arr
     * @return
     */
    ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for(int i = 1; i < arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        ListNode head = singleLinkedList.createLinkedList(new int[]{1,2,3,4,5});
        head = singleLinkedList.insertFirstNode(head, new ListNode(0));
        head = singleLinkedList.insertLastNode(head, new ListNode(6));
        head = singleLinkedList.insertMiddleNode(head, new ListNode(33), 3);

        ListNode p = head;
        while(p != null){
            System.out.println(p.val);
            p = p.next;
        }
        System.out.println("----------------------");

        head = singleLinkedList.createLinkedList(new int[]{5,6,7,8,9});
        head = singleLinkedList.deleteFirstNode(head);
        head = singleLinkedList.deleteLastNode(head);
        head = singleLinkedList.deleteMiddleNode(head, 2);

        p = head;
        while(p != null){
            System.out.println(p.val);
            p = p.next;
        }

    }

    /**
     * 在单链表头部插入新元素
     * @param head
     * @return
     */
    ListNode insertFirstNode(ListNode head, ListNode newNode) {
        newNode.next = head;
        head = newNode;
        return head;
    }

    /**
     * 在单链表尾部插入新元素
     * @param head
     * @return
     */
    ListNode insertLastNode(ListNode head, ListNode newNode) {
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = newNode;

        return head;
    }

    /**
     * 在单链表中间插入新元素
     * @param head
     * @param newNode
     * @param index
     * @return
     */
    ListNode insertMiddleNode(ListNode head, ListNode newNode, int index) {
        ListNode p = head;
        // 找到第 index-1 个节点（前驱节点）
        for(int i=0; i<index-2; i++) {
            if (p.next == null) {
                return head; // index 超出链表长度
            }
            p = p.next;
        }

        // 正确顺序，"先连后，再断前"
        newNode.next = p.next;
        p.next = newNode;

        return head;
    }

    /**
     * 删除单链表第一个节点
     * @param head
     * @return
     */
    ListNode deleteFirstNode(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        head = head.next;
        return head;
    }

    /**
     * 删除单链表最后一个节点
     * @param head
     * @return
     */
    ListNode deleteLastNode(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode p = head;
        // 找到倒数第二个节点
        while (p.next.next != null) {
            p = p.next;
        }
        // 把尾节点从链表中摘除
        p.next = null;

        return head;
    }

    /**
     * 删除单链表的第 index 个节点
     * @param head
     * @param index
     * @return
     */
    ListNode deleteMiddleNode(ListNode head, int index) {
        if (head == null || index < 1) {
            return head;
        }

        // 删除第一个节点的特殊情况
        if (index == 1) {
            return head.next;
        }

        ListNode p = head;
        for (int i = 0; i<index-2; i++) {
            if (p.next == null) {
                return head; // index 超出链表长度
            }
            p = p.next;
        }

        p.next = p.next.next;

        return head;
    }
}
