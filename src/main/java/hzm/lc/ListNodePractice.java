package hzm.lc;

import java.util.HashMap;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}

public class ListNodePractice {
    //链表的首个公共节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode lA = headA, lB = headB;
        while (lA != lB) {
            if (lA != null) {
                lA = lA.next;
            } else lA = headB;
            if (lB != null) {
                lB = lB.next;
            } else lB = headA;
        }
        return lA;
    }

    //反转链表
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    //回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode l1 = head, l2 = head;
        Stack<Integer> stack = new Stack<>();
        while (l1 != null) {
            stack.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            if (stack.pop() != l2.val) {
                break;
            }
            l2 = l2.next;
        }
        if (l2 == null) {
            return true;
        } else {
            return false;
        }
    }

    //环形链表
    public boolean hasCycle(ListNode head) {
        if(head == null ||  head.next == null) {
            return false;
        }
        HashMap<ListNode, Integer> map = new HashMap<>();
        ListNode l1 = head;
        Integer i = 0;
        while (l1 != null) {
            Integer integer = map.putIfAbsent(l1.next, i);
            if(integer != null) {
                return true;
            }
            i++;
            l1 = l1.next;
        }
        return false;
    }

    //环形链表的第一个节点
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        HashMap<ListNode, Integer> map = new HashMap<>();
        Integer i = 0;
        while (head != null) {
            Integer integer = map.putIfAbsent(head, i);
            if(integer != null) {
                return head;
            }
            i++;
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        //l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        //mergeTwoLists(l1, l2);
        //addTwoNumbers(l1, l2);
        removeNthFromEnd(l1, 2);
    }

    //合并两个有序链表
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        while (list1 != null && list2 != null) {
            ListNode tmp = null;
            if(list1.val <= list2.val) {
                tmp = list1;
                list1 = list1.next;
            } else {
                tmp = list2;
                list2 = list2.next;
            }
            cur.next = tmp;
            cur = cur.next;
        }
        if(list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return head.next;
    }

    //链表两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        Integer carry = 0;
        while(l1 != null && l2 != null) {
            Integer curp = (carry + l1.val + l2.val) % 10;
            carry = (carry + l1.val + l2.val) / 10;
            cur.next = new ListNode(curp);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(carry == 0) {
            if(l1 != null) {
                cur.next = l1;
            } else {
                cur.next = l2;
            }
        } else {
            while(l1 != null) {
                Integer curp = (carry + l1.val) % 10;
                carry = (carry + l1.val) / 10;
                cur.next = new ListNode(curp);
                cur = cur.next;
                l1 = l1.next;
            }
            while(l2 != null) {
                Integer curp = (carry + l2.val) % 10;
                carry = (carry + l2.val) / 10;
                cur.next = new ListNode(curp);
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
            cur = cur.next;
        }
        return head.next;
    }

    //删除链表的倒数第N个节点
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;
        while (n > 0) {
            first = first.next;
            n--;
        }
        while (first != null && first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

}
