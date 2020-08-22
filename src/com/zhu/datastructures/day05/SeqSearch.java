package com.zhu.datastructures.day05;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zpm
 * @version 1.0
 */
public class SeqSearch {

    static int[] arr = {5, 5, 5, 5, 5, 5, 5, 5};
    static List<Integer> list = new ArrayList<>();


    public static int seqFind(int[] arr, int destination) {
        for (int i = 0; i < arr.length; i++) {
            if (destination == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
