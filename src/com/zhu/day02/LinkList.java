package com.zhu.day02;

import java.util.Stack;
/**
 * @author ZPM
 * @version 1.0
 */
public class LinkList {
    private final Node head = new Node(0, null);

    private int size;

    public LinkList() {
    }


    public Node getHead() {
        return head;
    }

    public void add(int data) {
        Node current = new Node(data, null);
        Node tail = getTail();
        tail.next = current;
        size++;
    }

    private Node getTail() {
        if (head.next == null) {
            return head;
        }
        Node now = head;
        while (now.next != null) {
            now = now.next;
        }
        return now;
    }


    static class Node {
        private final int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder builder = new StringBuilder("[");
        while (node.next != null) {
            node = node.next;
            builder.append(node.data).append(node.next == null ? "" : ", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public void reverse(Node head) {
        Node current = head.next;
        Node reverseHead = new Node(0, null);
        Node next;
        while (current != null) {
            next = current.next;
            current.next = reverseHead.next;
            reverseHead.next = current;
            current = next;
        }
        head.next = reverseHead.next;
    }

    public void reversePrint(Node head) {
        if (head.next != null) {
            reversePrint(head.next);
        }
        if (head == this.head) {
            return;
        }
        System.out.print(head.data + " ");
    }

    public void reversePrintWithStack(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        Node cur = head.next;
        while (cur != null) {
            stack.push(cur.data);
            cur = cur.next;
        }
        System.out.println(stack);
    }

    public Node merge(Node head1, Node head2) {
        Node newHead = new Node(0, null);
        Node node1 = head1;
        while (node1.next != null) {
            node1 = node1.next;
        }
        node1.next = head2.next;
        newHead.next = head1.next;

        return newHead;
    }

    public Node sort(Node head, boolean isDesc) {
        Node[] objects = toArray(head);
        for (int i = 1; i < objects.length; i++) {
            boolean flag = true;
            for (int j = 0; j < objects.length - i; j++) {
                if (objects[j].data > objects[j + 1].data) {
                    Node temp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        Node newHead = new Node(0, null);
        if (isDesc) {
            for (Node node : objects) {
                node.next = newHead.next;
                newHead.next = node;
            }
        } else {
            for (Node node : objects) {
                node.next = null;
                Node cur = newHead;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
            }
        }
        head.next = newHead.next;

        return head;
    }

    public Node[] toArray(Node head) {
        Node[] obj = new Node[size];
        int i = 0;
        Node current = head.next;
        while (current != null) {
            obj[i++] = current;
            current = current.next;
        }
        return obj;
    }

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.add(1);
        linkList.add(9);
        linkList.add(3);
        linkList.add(7);
        linkList.add(5);


        LinkList linkList1 = new LinkList();
        linkList.add(2);
        linkList.add(0);
        linkList.add(6);
        linkList.add(8);
        linkList.add(4);

        Node newHead = linkList.merge(linkList1.getHead(), linkList.getHead());
        Node sort = linkList.sort(newHead, false);
        Node current = sort.next;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

}


