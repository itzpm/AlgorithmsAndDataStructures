package com.zhu.datastructures.tree;

/**
 * @author zpm
 * @version 1.0
 */
public final class Node {
    Node left;
    Node right;
    /**
     * 设置指针的类型,如果为0说明指向的是左孩子,如果为1说明指向的是前驱
     */
    int leftType;

    /**
     * 设置指针的类型,如果为0说明指向的是右孩子,如果为1说明指向的是后继
     */
    int rightType;
    int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return
                "data=" + data;
    }
}
