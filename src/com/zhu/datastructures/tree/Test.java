package com.zhu.datastructures.tree;

/**
 * @author zpm
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(0);
    }
}
