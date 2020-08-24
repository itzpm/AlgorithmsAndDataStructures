package com.zhu.algorithms.dac;

/**
 * @author zpm
 * @version 1.0
 */
public class HanoiTower {
    static int count;

    public static void main(String[] args) {
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " 移动到 " + c);
        } else {
            //将上面的n-1个移动到b
            hanoiTower(num - 1, a, c, b);
            //从a上将最下面的移动到c
            System.out.println("第" + num + "个盘从 " + a + " 移动到 " + c);
            //在将b上的n-1个移动到c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
