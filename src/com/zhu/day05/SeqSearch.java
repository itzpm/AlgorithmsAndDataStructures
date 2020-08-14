package com.zhu.day05;

/**
 * @author zpm
 * @version 1.0
 */
public class SeqSearch {

    static int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        System.out.println(binarySearch(arr, 6, 0, arr.length - 1));
    }

    public static int seqFind(int[] arr, int destination) {
        for (int i = 0; i < arr.length; i++) {
            if (destination == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int destination, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == destination) {
            return mid;
        } else if (arr[mid] > destination) {
            return binarySearch(arr, destination, left, mid - 1);
        } else {
            return binarySearch(arr, destination, mid + 1, right);
        }
    }
}
