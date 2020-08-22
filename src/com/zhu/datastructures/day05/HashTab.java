package com.zhu.datastructures.day05;

/**
 * @author zpm
 * @version 1.0
 */
public class HashTab {
    private final int maxSize;
    LinkedNode[] linkedNodes;

    public HashTab(int maxSize) {
        this.maxSize = maxSize;
        this.linkedNodes = new LinkedNode[maxSize];
    }

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        hashTab.addLink(new Node(1));
        hashTab.addLink(new Node(12));
        hashTab.addLink(new Node(22));
        hashTab.addLink(new Node(32));
        hashTab.addLink(new Node(15));
        hashTab.addLink(new Node(51));
        hashTab.addLink(new Node(5));
        hashTab.addLink(new Node(4));
        hashTab.addLink(new Node(7));
        hashTab.print();
        System.out.println(hashTab.remove(22));
        System.out.println(hashTab.remove(15));
        System.out.println("=========");
        hashTab.print();
    }

    public void addLink(Node node) {
        if (node == null) {
            System.out.println("添加失败");
            return;
        }
        int index = index(node.id);
        LinkedNode no = linkedNodes[index];
        if (linkedNodes[index] == null) {
            no = linkedNodes[index] = new LinkedNode();
        }
        no.add(node);
    }

    public void print() {
        for (int i = 0; i < maxSize; i++) {
            if (linkedNodes[i] != null) {
                System.out.print(i + "号链表:");
                linkedNodes[i].print();
                System.out.println();
            }
        }
    }

    public Node remove(int id) {
        int index = index(id);
        LinkedNode nodes = linkedNodes[index];
        return nodes.remove(id);
    }

    public int index(int id) {
        return id % maxSize;
    }
}

class LinkedNode {
    Node head;

    public void add(Node node) {
        if (checkNull(node)) {
            System.out.println("不能插入空的节点");
            return;
        }
        if (checkNull(head)) {
            head = node;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public Node remove(int id) {
        if (checkNull(head)) {
            return null;
        }
        Node current = head;
        if (current.id == id) {
            head = head.next;
            return current;
        }
        Node next = head.next;
        while (next != null) {
            if (next.id == id) {
                current.next = next.next;
                return next;
            }
            current = next;
            next = next.next;
        }
        return null;
    }

    private boolean checkNull(Object o) {
        return o == null;
    }

    public void print() {
        if (checkNull(head)) {
            System.out.println("链表为空");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
    }
}

class Node {
    int id;
    Node next;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
