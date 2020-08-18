package com.zhu.tree;

/**
 * @author zpm
 * @version 1.0
 */
public class TraverseTree<T> {
    private TreeNode<T> head;

    public static void main(String[] args) {
        TraverseTree<Integer> traverseTree = new TraverseTree<>();
        TreeNode<Integer> head = new TreeNode<>(1);
        TreeNode<Integer> l = new TreeNode<>(2);
        TreeNode<Integer> r = new TreeNode<>(3);
        TreeNode<Integer> ll = new TreeNode<>(4);
        traverseTree.head = head;
        head.left = l;
        head.right = r;
        l.left = ll;
        traverseTree.remove(head, 2);
        traverseTree.preOrder();
    }

    public void remove(TreeNode<T> node, T data) {
        if (node == null) {
            return;
        }
        if (node.data == data || node.data.equals(data)) {
            node.left = null;
            node.right = null;
            head = null;
        } else {
            removeIf(node, data);
        }
    }

    private void removeIf(TreeNode<T> node, T data) {
        if (node == null) {
            return;
        }
        if (node.left != null && (node.left.data == data || node.left.data.equals(data))) {
            node.left = null;
        }

        if (node.right != null && (node.right.data == data || node.right.data.equals(data))) {
            node.right = null;
        }
        removeIf(node.left, data);
        removeIf(node.right, data);

    }

    public void preOrder() {
        preOrder(head);
    }

    public void preOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void midOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        System.out.println(node.data);
        midOrder(node.right);
    }

    public void behindOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        behindOrder(node.left);
        behindOrder(node.right);
        System.out.println(node.data);
    }

    public TreeNode<T> preSearch(TreeNode<T> head, T key) {
        if (head == null) {
            return null;
        }
        if (head.data == key || key.equals(head.data)) {
            return head;
        }
        TreeNode<T> treeNode = null;
        treeNode = preSearch(head.left, key);
        if (treeNode != null) {
            return treeNode;
        }
        treeNode = preSearch(head.right, key);
        return treeNode;
    }

    public TreeNode<T> midSearch(TreeNode<T> head, T key) {
        if (head == null) {
            return null;
        }
        TreeNode<T> treeNode = null;
        treeNode = midSearch(head.left, key);
        if (treeNode != null) {
            return treeNode;
        }
        if (head.data == key || key.equals(head.data)) {
            return head;
        }
        treeNode = midSearch(head.right, key);
        return treeNode;
    }


    public TreeNode<T> behindSearch(TreeNode<T> head, T key) {
        if (head == null) {
            return null;
        }
        TreeNode<T> treeNode = null;
        treeNode = behindSearch(head.left, key);
        if (treeNode != null) {
            return treeNode;
        }
        treeNode = behindSearch(head.right, key);
        if (treeNode != null) {
            return treeNode;
        }
        if (head.data == key || key.equals(head.data)) {
            return head;
        }
        return null;
    }


    static class TreeNode<T> {
        TreeNode<T> left;
        TreeNode<T> right;
        T data;

        public TreeNode(T data) {
            this.data = data;
        }
    }
}
