package com.zhu.datastructures.tree;

/**
 * @author zpm
 * @version 1.0
 */
public class BinarySortTree {

    private Node root;

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 5, 1};
        for (int j : arr) {
            Node node = new Node(j);
            tree.add(node);
        }
        tree.pre(tree.root);
        System.out.println();
        tree.delete(22);
        tree.pre(tree.root);
    }

    public void setRoot(Node root) {
        this.root = root;
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
    }

    public void pre(Node node) {
        if (node == null) {
            return;
        }
        pre(node.left);
        System.out.print(node.data + " ");
        pre(node.right);
    }

    public Node findMin(Node node) {
        Node l, r;
        if (node == null) {
            return null;
        }
        l = findMin(node.left);
        r = findMin(node.right);
        return min(node, min(l, r));
    }

    private Node min(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        return node1.data > node2.data ? node2 : node1;
    }

    private int delMinNode(Node node) {
        if (node == null) {
            return 0;
        }
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delete(target.data);
        return target.data;
    }

    public Node searchTarget(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.parent(value);
    }

    public void delete(int value) {
        if (root == null) {
            return;
        }
        Node targetNode = searchTarget(value);
        if (targetNode == root) {
            if (targetNode.left == null && targetNode.right == null) {
                root = null;
                return;
            }
            Node right = root.right;
            if (right != null) {
                Node temp = right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = root.left;
                root = right;
            } else {
                root = root.left;
            }
            return;
        }
        //如果给定的值没有就返回空不进行下面的步骤
        if (targetNode == null) {
            System.out.println("没有给定的值");
            return;
        }
        //可以不用判断data==value,因为targetNode不为空说明肯定找到了,这里root的左右节点都是空说明找到的就是root节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        Node parent = searchParent(value);
        //如果要删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left == targetNode) {
                parent.left = null;
                return;
            }
            if (parent.right == targetNode) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
//            Node target = findMin(targetNode.right);
//            Node p = searchParent(target.data);
//            if (parent.left == targetNode) {
//                parent.left = target;
//            } else if (parent.right == targetNode) {
//                parent.right = target;
//            }
//            if (targetNode.left != target) {
//                target.left = targetNode.left;
//            }
//            if (targetNode.right != target) {
//                target.right = targetNode.right;
//            }
//            if (p.right == target) {
//                p.right = null;
//            } else if (p.left == target) {
//                p.left = null;
//            }
            targetNode.data = delMinNode(targetNode.right);
        } else {
            if (targetNode.left != null) {
                if (parent.data > targetNode.left.data) {
                    parent.left = targetNode.left;
                } else {
                    parent.right = targetNode.left;
                }
            } else {
                if (parent.data > targetNode.right.data) {
                    parent.left = targetNode.right;
                } else {
                    parent.right = targetNode.right;
                }
            }
        }


    }

    static class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
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
