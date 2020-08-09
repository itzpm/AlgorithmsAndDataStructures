package com.zhu.day02;

/**
 * @author ZPM
 * @version 1.0
 */
public class Josephu {

    static class Boy {
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }
    }

    static class Circle {
        private Boy first;
        private int size;

        public void addBoy(int nums) {
            size += nums;
            if (nums < 1) {
                System.out.println("数字不合法");
                return;
            }
            Boy cur = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    first.next = first;
                    cur = first;
                } else {
                    cur.next = boy;
                    boy.next = first;
                    cur = boy;
                }
            }
        }

        public void showBoy() {
            if (first == null) {
                System.out.println("当前没有小孩...");
                return;
            }
            Boy cur = first;
            while (true) {
                System.out.println("小孩" + cur.no + "号");
                if (cur.next == first) {
                    break;
                }
                cur = cur.next;
            }
        }

        public void weedOut(int start, int counts) {
            if (first == null || start < 1 || counts < 1) {
                System.out.println("输入不合法...");
                return;
            }

            //拿到最后一个节点
            Boy helper = first;
            while (helper.next != first) {
                helper = helper.next;
            }

            //找到给定的起始位置
            for (int i = 0; i < start - 1; i++) {
                first = first.next;
                helper = helper.next;
            }

            while (helper != first) {
                for (int i = 0; i < counts - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.printf("%d号小孩出圈\n",first.no);
                first = first.next;
                helper.next = first;
            }
            System.out.printf("%d号小孩出圈\n",first.no);
        }
    }


    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.addBoy(41);
//        circle.showBoy();
        circle.weedOut(1,3);
    }
}
