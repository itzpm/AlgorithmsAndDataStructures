package com.zhu;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author ZPM
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
//        PrintStream p = new PrintStream("a.txt");
//        System.setOut(p);
//        p.println("a b c");
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//        System.out.println("hello world");

        int[] data = new int[]{5, 3, 6, 2, 1, 9, 4, 8, 7};
        int[] arr = new int[5];
        System.arraycopy(data, 4, arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }
}
