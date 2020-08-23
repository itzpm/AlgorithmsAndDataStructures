package com.zhu.datastructures.avl;

/**
 * @author zpm
 * @version 1.0
 */
public class AvlTreeDemo {
    private static Node root;

    public static void main(String[] args) {
        AvlTreeDemo tree = new AvlTreeDemo();
        int[] arr = {10, 11, 7, 6, 8, 9};
        for (int j : arr) {
            Node node = new Node(j);
            tree.add(node);
        }
//        tree.leftRotate(root);
//        tree.rightRotate(root);
        System.out.println(tree.height(root, true));
        System.out.println(tree.height(root, false));
        System.out.println(root);
    }

    private int h(Node root) {
        return root.h(root);
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

        while (height(root, true) - height(root, false) < -1) {
            if (root.right != null && height(root.right, true) < height(root.right, false)) {
                leftRotate(root.right);
            }else if(root.right != null && height(root.right, true) > height(root.right, false)){
                rightRotate(root.right);
            }
            leftRotate(root);
        }
        while (height(root, true) - height(root, false) > 1) {
            if (root.left != null && height(root.left, true) > height(root.left, false)) {
                rightRotate(root.left);
            } else if (root.left != null && height(root.left, true) < height(root.left, false)) {
                leftRotate(root.left);
            }
            rightRotate(root);
        }
    }

//    public void rotateLeft(Node node) {
//        if (node == null) {
//            return;
//        }
//        if (height(node, true) - height(node, false) > 1) {
//            if (node.left != null && height(node.left, true) > height(node.left, false)) {
//                leftRotate(node.left);
//            } else if (node.left != null && height(node.left, true) < height(node.left, false)) {
//                rightRotate(node.left);
//            } else {
//                rightRotate(node);
//            }
//            rotateLeft(node.left);
//            rotateLeft(node.right);
//        }
//    }
//
//    public void rotateRight(Node node) {
//        if (node == null) {
//            return;
//        }
//        if (height(node, true) - height(node, false) > 1) {
//            if (node.right != null && height(node.right, true) > height(node.right, false)) {
//                rightRotate(node.right);
//            } else if (node.right != null && height(node.right, true) < height(node.right, false)) {
//                leftRotate(node.right);
//            } else {
//                leftRotate(node);
//            }
//            rotateRight(node.left);
//            rotateRight(node.right);
//        }
//    }

    public void leftRotate(Node node) {
        if (node == null) {
            return;
        }
        System.out.println("左旋了");
        node.leftRotate();
    }

    public void rightRotate(Node node) {
        if (node == null) {
            return;
        }
        System.out.println("右旋了");
        node.rightRotate();
    }

    public int height(Node node, boolean flag) {
        return node.height(flag);
    }

    static class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
        }

        /**
         * 如果flag = true返回左子树高度
         * 反之返回右子树高度
         *
         * @param flag
         * @return h
         */
        public int height(boolean flag) {
            if (flag) {
                if (left == null) {
                    return 0;
                }
                return h(left);
            } else {
                if (right == null) {
                    return 0;
                }
                return h(right);
            }
        }

        public void rightRotate() {
            //先建立一个新节点存放当前节点的值
            Node newNode = new Node(data);

            //将新节点的左边指向当前节点的左边的右边
            newNode.left = left.right;

            //将新节点的右边指向当前节点的右边
            newNode.right = right;

            //将当前节点的值修改为当前节点左边的值
            data = left.data;

            //将当前节点的左边指向当前节点的左边的左边
            left = left.left;

            //将当前节点的右边指新节点
            right = newNode;
        }

        public void leftRotate() {
            //先用一个新节点把当前节点的值存起来
            Node newNode = new Node(data);

            //新节点的左边为当前节点的左边
            newNode.left = left;

            //新节点的右边等于当前节点的右边的左边
            newNode.right = right.left;

            //当前节点的值改为当前节点的右边的值
            data = right.data;

            //当前节点的右边为右边的右边
            right = right.right;

            //让根节点的左边为新节点
            left = newNode;
        }

        public int h(Node node) {
            if (node == null) {
                return 0;
            }
            int l, r;
            l = h(node.left) + 1;
            r = h(node.right) + 1;
            return Math.max(l, r);
        }

        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (this.data < node.data) {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            } else {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            }
        }


        public Node search(int value) {
            if (this.data == value) {
                return this;
            } else if (this.data > value) {
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }

        private boolean judge(Node node, int value) {
            return node != null && node.data == value;
        }

        public Node parent(int value) {
            if (judge(left, value) || judge(right, value)) {
                return this;
            } else {
                if (value < data && left != null) {
                    return left.parent(value);
                } else if (value >= data && right != null) {
                    return right.parent(value);
                } else {
                    return null;
                }
            }
        }
    }
}
