package com.zhu.day04;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ZPM
 * @version 1.0
 */
public class Sort {

    private static int count;
    private static final int[] array = {8, 9, 4, 5, 7};

    public static int[] getArray() {
        int[] arr = new int[80 * 1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() + 1) * 1000);
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr) {
        long l;
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空...");
        } else {
            l = System.currentTimeMillis();
            for (int i = 1; i < arr.length; i++) {
                boolean flag = true;
                for (int j = 0; j < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            }
            System.out.println("一共花费了" + (System.currentTimeMillis() - l) / (double) 1000 + "秒");
            return arr;
        }
    }

    public static int[] selectSort(int[] arr) {
        long l;
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空...");
        } else {
            l = System.currentTimeMillis();
            for (int i = 0; i < arr.length - 1; i++) {
                int max = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] > arr[max]) {
                        max = j;
                    }
                }
                count++;
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }
        System.out.println("一共花费了" + (System.currentTimeMillis() - l) / (double) 1000 + "秒");
        return arr;
    }


    @Test
    public void insertSort() {
        //6,3,2,5,1    temp=1  i=3
        int[] array = {6, 3, 2, 5, 1};
        long l = System.currentTimeMillis();
        int max;
        int temp;
        for (int i = 1; i < array.length; i++) {
            max = i;
            temp = array[i];
            while (max > 0 && (array[max - 1] > temp)) {
                array[max] = array[max - 1];
                max--;
            }
            count++;
            array[max] = temp;
            System.out.printf("第%d次排序结果为:%s\n", i, Arrays.toString(array));
        }
        System.out.println("一共花费了" + (System.currentTimeMillis() - l) / (double) 1000 + "秒");
        System.out.println("共计" + count + "次");
    }


    /**
     * 交换的希尔排序
     *
     * @throws InterruptedException
     */
    @Test
    public void shellSort() throws InterruptedException {
        int temp;
        int index = array.length / 2;
        for (int k = index; k > 0; k /= 2) {
            for (int i = k; i < array.length; i++) {
                for (int j = i - k; j >= 0; j -= k) {
                    if (array[j] > array[j + k]) {
                        temp = array[j];
                        array[j] = array[j + k];
                        array[j + k] = temp;
                    }
                }
            }
            System.out.printf("第%d次排序结果为:%s\n", ++count, Arrays.toString(array));
        }
    }

    /**
     * 移动的希尔排序
     */
    @Test
    public void shellSort2() {
        //8, 9, 1, 7, 2, 3, 5, 4, 6, 0
        int temp;
        int max;
        int[] array = getArray();
        int index = array.length / 2;
        for (int k = index; k > 0; k /= 2) {
            for (int i = k; i < array.length; i++) {
                temp = array[i];
                max = i;
                while (max - k >= 0 && array[max - k] < temp) {
                    array[max] = array[max - k];
                    max -= k;
                }
                array[max] = temp;
            }
//            System.out.printf("第%d次排序结果为:%s\n", ++count, Arrays.toString(array));
        }
    }

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
