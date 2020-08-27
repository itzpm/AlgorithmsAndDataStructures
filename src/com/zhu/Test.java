package com.zhu;


/**
 * @author zpm
 * @version 1.0
 */
public class Test {
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
        Node reverse = reverse(head);
        System.out.println(reverse);
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newNode = reverse(head.next);
        Node t1 = head.next;
        t1.next = head;
        head.next = null;
        return newNode;
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
