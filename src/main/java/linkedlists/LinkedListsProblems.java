package linkedlists;

import java.util.Objects;

public class LinkedListsProblems {

    /**
     * LC 2130. Maximum twin sum of a Linkedlist
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {

        ListNode a = head, b = head.next, c = head;
        // get center of linked list and reverse first half
        while(c.next.next != null) {
            c = c.next.next;

            var temp = b.next;
            b.next = a;
            a = b;
            b = temp;
        }

        int pairSum = Integer.MIN_VALUE;
        // pointers a and b are set
        while(a != null && b != null) {
            pairSum = Math.max(pairSum, a.val + b.val);
            a = a.next;
            b = b.next;
        }

        return pairSum;
    }

    /**
     * LC 160. Intersection of Two Linked Lists
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        while(l1 != l2) {
            l1 = l1 != null ? l1.next : headB;
            l2 = l2 != null ? l2.next : headA;
        }

        return l1;
    }

    /**
     * LC 2095. Delete the middle of a LinkedList
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        // find the middle
        ListNode fp = head;
        ListNode sp = head;
        while (fp.next != null) {
            sp = sp.next; // is the middle node
            fp = fp.next;
            if (fp.next != null) {
                fp = fp.next;
            } else  {
                break;
            }
        }
        // delete it
        if (Objects.equals(fp, head)) {
            head = null;
        } else if (sp.next != null) {
            sp.val = sp.next.val;
            sp.next = sp.next.next;
        } else {
            head.next = null;
        }

        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
