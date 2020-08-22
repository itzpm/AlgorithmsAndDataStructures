package com.zhu.datastructures.tree;

/**
 * @author zpm
 * @version 1.0
 */
public class ThreadBinaryTree {

    private Node previous;

    public static void main(String[] args) {

        ThreadBinaryTree tree = new ThreadBinaryTree();

        Node head = new Node(1);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(8);
        Node node4 = new Node(10);
        Node node5 = new Node(14);

        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        tree.threadsNode(head);

        tree.midOrder(head);


    }

    public void threadsNode(Node node) {
        if (node == null) {
            return;
        }
        //线索化左节点
        threadsNode(node.left);

        /*线索化当前节点*/
        //先处理当前节点的前驱节点
        if (node.left == null) {
            node.left = previous;
            node.leftType = 1;
        }

        //处理后继节点
        if (previous != null && previous.right == null) {
            previous.right = node;
            previous.rightType = 1;
        }
        previous = node;


        //线索化右节点
        threadsNode(node.right);
    }


    public void midOrder(Node node) {
        if (node == null) {
            return;
        }

        if (node.leftType != 1) {
            midOrder(node.left);
        }
        System.out.println(node);
        if (node.rightType != 1) {
            midOrder(node.right);
        }
    }
}
