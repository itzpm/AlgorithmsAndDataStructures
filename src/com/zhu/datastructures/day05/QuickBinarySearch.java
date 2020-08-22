package com.zhu.datastructures.day05;

/**
 * @author zpm
 * @version 1.0
 */
public class QuickBinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 54, 58, 553, 776, 3356};
//        for (int i = 1; i <= 100; i++) {
//            arr[i - 1] = i;
//        }
        System.out.println(binarySearch(arr, 54, 0, arr.length - 1));
    }

    public static int binarySearch(int[] arr, int destination, int left, int right) {
        System.out.println("hello world");
        if (left > right) {
            return -1;
        }
        //在1 2 3 4 ...这种有序的序列上效果好,1 3 8 111 555 666这种效果就不好了  给的目标必须在这个范围内
        int mid = left + (right - left) * (destination - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] == destination) {
            return mid;
        } else if (arr[mid] > destination) {
            return binarySearch(arr, destination, left, mid - 1);
        } else {

            return binarySearch(arr, destination, mid + 1, right);
        }
    }
}