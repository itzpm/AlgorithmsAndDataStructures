package com.zhu.datastructures.day03;

import java.util.Arrays;

/**
 * @author ZPM
 * @version 1.0
 */
public class EightQueen {
    private final int maxSize = 8;
    private final int[] arr = new int[maxSize];
    static int count;
    static int num;

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.println(count);
        System.out.printf("一共判断了%d次", num);
    }


    private void check(int n) {
        //如果n等于最大值说明已经放好了前面的8个皇后
        if (n == maxSize) {
            print();
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            //arr[n]代表第n行 i这里代表第n行的第i列
            arr[n] = i;
            if (judge(n)) {
                //如果不冲突继续放下一个皇后在下一行
                check(n + 1);
            }
            //如果冲突就继续放第n行的下一列
        }
    }


    /**
     * @param n 表示第几个皇后
     * @return
     */
    private boolean judge(int n) {
        num++;
        //一个元素代表一行,这里不用判断行,因为它本身就是自增的,
        for (int i = 0; i < n; i++) {
            //arr[i] == arr[n] 表示是否在同一列上
            //Math.abs(n - i) == Math.abs(arr[n] - arr[i])) 表示是否在同一斜线上
            //将一个一维数组作为二维数组,第一个代表第一行其中的数字代表第几列
            if (arr[i] == arr[n] || (Math.abs(n - i) == Math.abs(arr[n] - arr[i]))) {
                //0 1
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        Arrays.stream(arr).forEach((num) -> System.out.print(num + " "));
        System.out.println();
    }
}
