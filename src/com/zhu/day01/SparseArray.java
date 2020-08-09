package com.zhu.day01;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author ZPM
 * @version 1.0
 * 稀疏矩阵
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {
        //创建一个11*11的矩阵 0表示没有棋子 1表示黑子 2表示蓝子
        int[][] arr = new int[11][11];
        System.out.println(arr.length);
        int sum = 0;
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[2][5] = 10;
        System.out.println("=================压缩前===================");
        for (int[] a : arr) {
            for (int num : a) {
                if (num != 0) {
                    sum++;
                }
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }
        System.out.println("=================压缩后===================");

        int[][] sparseArr = new int[sum + 1][3];
        //表示 原始数组 一共十一行 十一列 sum个有效值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int row = 1;
        //arr.length行是多少
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArr[row][0] = i;
                    sparseArr[row][1] = j;
                    sparseArr[row][2] = arr[i][j];
                    row++;
                }
            }
        }


        System.out.println("==============将压缩的二维数组保存到文件中=============");
        PrintStream p = new PrintStream("map.data");
        System.setOut(p);

        for (int[] a : sparseArr) {
            for (int n : a) {
                p.print(n + " ");
            }
            System.out.println();
        }
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("=================恢复后===================");
        BufferedReader bf = new BufferedReader(new FileReader("map.data"));
        String str = "";
        while ((str = bf.readLine()) != null) {
            System.out.println(str);
        }
        int[] temp = sparseArr[0];
        int[][] reset = new int[temp[0]][temp[1]];
        for (int i = 1; i < sparseArr.length; i++) {
            reset[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] a : reset) {
            for (int num : a) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }
    }

}
