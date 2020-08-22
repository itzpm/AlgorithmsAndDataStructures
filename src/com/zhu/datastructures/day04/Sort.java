package com.zhu.datastructures.day04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ZPM
 * @version 1.0
 */
public class Sort {

    private static int count;
    Random r = new Random();

    public static int[] getArray() {
        int[] arr = new int[1000 * 1000 * 100];
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

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public Integer[] getSmallArray() {
        return new Integer[]{2, 10, 8, 22, 34, 5, 12, 28, 21, 11};
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
        int[] array = getArray();
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
    public void shellSort2(int[] array) {
        //8, 9, 1, 7, 2, 3, 5, 4, 6, 0
        int temp;
        int max;
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

    @Test
    public void testQuick() {
        Integer[] arr = getSmallArray();
        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] array = getArray();
        long time = System.currentTimeMillis();
        shellSort2(array);
        quickSort2(array, 0, array.length - 1);
        System.out.println(System.currentTimeMillis() - time);
//        System.out.println(Arrays.toString(a));
//        System.out.println(easyQuickSort(Arrays.asList(arr)));

    }

    public void quickSort2(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int leader = arr[(left + right) / 2];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && leader <= arr[j]) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
            }
            while (i < j && leader > arr[i]) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
            }
        }
        arr[j] = leader;
        quickSort2(arr, left, j - 1);
        quickSort2(arr, j + 1, right);
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int temp;
        //最左边的为准基数
        int flag = arr[left];
        while (i != j) {
            //从右侧找到一个比标记小的值
            while (arr[j] > flag && i < j) {
                j--;
            }
            //从左侧找到一个比标记大的值
            while (arr[i] <= flag && i < j) {
                i++;
            }
            //交换找到的值,使左边小,右边大
            if (i < j) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        //完成while循环,此时i=j
        //交换准基数和相遇值,即将中值归位  i j都一样出来都是i=j
        arr[left] = arr[j];
        arr[j] = flag;

        //将中值左,右侧的数据分别进行排序(递归)
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);

    }

    public List<Integer> easyQuickSort(List<Integer> list) {
        if (list == null || list.size() < 1) {
            return list;
        }
        int stand = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        //如果起始值i=0会爆栈
        for (int i = 1; i < list.size(); i++) {
            if (stand >= list.get(i)) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        List<Integer> list1 = easyQuickSort(left);
        List<Integer> list2 = easyQuickSort(right);
        List<Integer> result = new ArrayList<>(list1);
        result.add(stand);
        result.addAll(list2);
        return result;
    }

    @Test
    public void testMergeSort() {
//        int[] array = {2, 10, 8, 22, 34, 5, 12, 28, 21, 11};
        int[] array = getArray();
        int[] temp = new int[array.length];
        long time = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println((System.currentTimeMillis() - time) / (double) 1000);
//        System.out.println(Arrays.toString(array));
    }

    public void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int center = (left + right) / 2;

        mergeSort(array, left, center, temp);
        mergeSort(array, center + 1, right, temp);
        merge(array, left, right, center, temp);
    }

    public void merge(int[] arr, int left, int right, int center, int[] tempArr) {
        int mid = center + 1;
        int temp = left;
        int index = left;

        while (left <= center && mid <= right) {
            if (arr[left] < arr[mid]) {
                tempArr[temp++] = arr[left++];
            } else {
                tempArr[temp++] = arr[mid++];
            }
        }

        while (mid <= right) {
            tempArr[temp++] = arr[mid++];
        }

        while (left <= center) {
            tempArr[temp++] = arr[left++];
        }
        System.arraycopy(tempArr, index, arr, index, right - index + 1);
    }

    @Test
    public void testBucket() throws InterruptedException {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = getArray();
        long time = System.currentTimeMillis();
        bucketSort(arr);
        System.out.println(System.currentTimeMillis() - time);
        TimeUnit.SECONDS.sleep(100000);
        arr[0] = 1;
    }

    private int max(int[] arr) {
        int result = arr[0];
        for (int num : arr) {
            result = Math.max(num, result);
        }
        return result;
    }

    public void bucketSort(int[] arr) {
        String str = max(arr) + "";
        int[][] buckets = new int[10][arr.length];
        int[] tempArr = new int[10];
        for (int n = 0, y = 1; n < str.length(); n++, y *= 10) {
            for (int k : arr) {
                int num = k / y % 10;
                //tempArr[num]++表示第几个桶有几个有效数字
                buckets[num][tempArr[num]++] = k;
            }
            int index = 0;
            for (int i = 0; i < buckets.length; i++) {
                if (tempArr[i] != 0) {
                    //对应的桶里面有多少个有效数字
                    int count = tempArr[i];
                    for (int j = 0; j < count; j++) {
                        arr[index++] = buckets[i][j];
                    }
                }
                tempArr[i] = 0;
            }
        }
//        System.out.println("arr:" + Arrays.toString(arr));
//        System.out.println("tempArr:" + Arrays.toString(tempArr));
    }
}
