package com.zhu.tree;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[80000000];

        for (int i = 0; i < 80000000; i++) {
            arr[i] = i;
        }
        long time = System.currentTimeMillis();
        heapSort(arr);
        System.out.println("花费了:" + (System.currentTimeMillis() - time) + "毫秒");
    }

    public static void heapSort(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustment(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustment(arr, 0, i);
        }
//        System.out.println(Arrays.toString(arr));
    }

    private static void adjustment(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = i * 2 + 1) {
            if ((k + 1 < length) && (arr[k] < arr[k + 1])) {
                //让k为右节点
                k = k + 1;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
