package com.zhu.datastructures.day02;

/**
 * @author zpm
 * @version 1.0
 */
public class ReverseLinkList {
    private static Node head;

    static {
        head = new Node();
        head.date = 1;

        Node node1 = new Node();
        node1.date = 2;

        Node node2 = new Node();
        node2.date = 3;

        Node node3 = new Node();
        node3.date = 4;

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
    }

    public static void main(String[] args) {
        ReverseLinkList list = new ReverseLinkList();
        Node node = list.reverseList(head);
        System.out.println(node);
    }

    Node reverseList(Node head) {
        // 1.递归结束条件
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转 ⼦链表
        Node newList = reverseList(head.next);
        // 改变 1，2节点的指向。
        // 通过 head.next获取节点2
        Node t1 = head.next;
        // 让 2 的 next 指向 2
        t1.next = head;
        // 1 的 next 指向 null.
        head.next = null;
        // 把调整之后的链表返回。
        return newList;
    }

    static class Node {
        int date;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "date=" + date +
                    '}';
        }
    }
}

