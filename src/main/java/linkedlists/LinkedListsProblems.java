package linkedlists;

import java.util.Objects;

public class LinkedListsProblems {

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
