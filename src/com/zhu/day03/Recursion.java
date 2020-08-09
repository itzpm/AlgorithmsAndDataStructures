package com.zhu.day03;

/**
 * @author ZPM
 * @version 1.0
 */
public class Recursion {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        miGong(arr);
    }

    public static void miGong(int[][] arr) {
        System.out.println("==========走前的路径==========");
        for (int[] a : arr) {
            for (int b : a) {
                System.out.print(b + "    ");
            }
            System.out.println();
        }
        findWay(arr, 1, 1);
        System.out.println("==========走后的路径==========");
        for (int[] a : arr) {
            for (int b : a) {
                System.out.print(b + "    ");
            }
            System.out.println();
        }
    }

    /**
     * 走的顺序 下->右->上->左
     */
    public static boolean findWay(int[][] map, int i, int j) {
        //如果if满足说明已经走到了终点
        if (map[6][5] == 2) {
            return true;
        } else {
            //还没有走过
            if (map[i][j] == 0) {
                //尝试走一下
                map[i][j] = 2;
                //如果下面可以走就走下面
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    //如果下面不可以走就走右边
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    //向左边走
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    //向上面走
                    return true;
                } else {
                    //说明是死点,置为三
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static int jieCheng(int n) {
        if (n <= 0) {
            return 1;
        } else {
            return jieCheng(n - 1) * n;
        }
    }
}
