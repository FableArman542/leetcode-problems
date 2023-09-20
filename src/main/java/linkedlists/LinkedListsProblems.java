package linkedlists;

import java.util.Objects;

public class LinkedListsProblems {

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
