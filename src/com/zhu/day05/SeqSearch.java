package com.zhu.day05;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zpm
 * @version 1.0
 */
public class SeqSearch {

    static int[] arr = {5, 5, 5, 5, 5, 5, 5, 5};
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(binarySearch2(arr, 5, 0, arr.length-1));
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

    public static List<Integer> binarySearch2(int[] arr, int destination, int left, int right) {
        if (left > right || arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if (arr[mid] == destination) {
            int temp = mid - 1;
            //向左边扫描
            while (true) {
                if (temp < 0 || arr[temp] != destination) {
                    break;
                }
                list.add(temp--);
            }
            list.add(mid);
            //向右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != destination) {
                    break;
                }
                list.add(temp++);
            }
            return list;
        } else if (arr[mid] > destination) {
            return binarySearch2(arr, destination, left, mid - 1);
        } else {
            return binarySearch2(arr, destination, mid + 1, right);
        }
    }
}
