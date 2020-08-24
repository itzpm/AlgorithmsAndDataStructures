package com.zhu.algorithms.search;

/**
 * @author zpm
 * @version 1.0
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(arr, 11));
        //1802
        System.out.println(multiply("123", "456"));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static String multiply(String num1, String num2) {
        int[] n1 = new int[num1.length()];
        int[] n2 = new int[num2.length()];
        int[] result = new int[n1.length + n2.length - 1];

        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            n1[i] = c1[i] - '0';
        }

        for (int i = 0; i < c2.length; i++) {
            n2[i] = c2[i] - '0';
        }

        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                result[i + j] += n1[i] * n2[j];
            }
        }

        for (int i = result.length - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }

        StringBuilder builder = new StringBuilder();
        for (int j : result) {
            builder.append(j);
        }
        return builder.toString();
    }
}
