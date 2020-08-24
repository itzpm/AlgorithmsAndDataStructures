package com.zhu.algorithms.dtai;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class DynamicDistribute {
    public static void main(String[] args) {
        dynamicDistribute();
    }

    public static void dynamicDistribute() {
        //物品的重量
        int[] weight = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};

        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;

        //在前i个物品中容量为j的背包能装下的物品的最大价值
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        for (int i = 1; i < v.length; i++) {
            //j表示背包的容量
            for (int j = 1; j < v[i].length; j++) {
                //如果背包容量小于当前物品的重量  放不下
                if (j < weight[i - 1]) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //如果放得下 当前物品+装入当前物品后再装入剩余的物品之后 和上一个物品谁的价值大就装谁
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - weight[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int[] arr : v) {
            System.out.println(Arrays.toString(arr));
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("背包放入了价值为:"+val[i-1]);
                j -= weight[i-1];
            }
            i--;
        }
    }
}
