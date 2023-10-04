package algithms;

import java.util.*;

public class LinkedListAlg {

    public static void main(String[] args) {
        ListNode h4 = new ListNode(4);
        ListNode h5 = new ListNode(5, h4);
        ListNode h3 = new ListNode(3, h4);
        ListNode h2 = new ListNode(2, h3);
        ListNode h1 = new ListNode(1, h2);

        LinkedListAlg linkedListAlg = new LinkedListAlg();

        linkedListAlg.reverseList(h1);
        System.out.println(h1 + " -> " + h1.next);
        System.out.println(h2 + " -> " + h2.next);
        System.out.println(h3 + " -> " + h3.next);
        System.out.println(h4 + " -> " + h4.next);
        System.out.println(h5 + " -> " + h5.next);

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode n1 = head;
        ListNode n2 = head.next;
        head.next = null;

        while (n2 != null) {
            ListNode temp = n2.next;
            n2.next = n1;

            n1 = n2;
            n2 = temp;
        }

        return head;
    }
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "" + val;
        }
    }
}
