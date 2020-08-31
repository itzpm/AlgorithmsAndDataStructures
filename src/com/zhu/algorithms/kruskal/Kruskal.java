package com.zhu.algorithms.kruskal;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class Kruskal {
    private final static int INF = Integer.MAX_VALUE;
    /**
     * 边的条数
     */
    private static int edgeNums;
    /**
     * 顶点数组
     */
    private static char[] vertx;

    /**
     * 邻接矩阵
     */
    private static int[][] matrix;

    static {
        vertx = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNums++;
                }
            }
        }
    }

    public static void main(String[] args) {
        kruskal();
    }

    public static void kruskal() {
        int index = 0;
        //表示边的顶点的集合
        int[] ends = new int[vertx.length];

        //存放边的集合
        Side[] result = new Side[vertx.length - 1];
        Side[] edges = getEdges();
        sort(edges);
        for (int i = 0; i < edgeNums; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //合并并查集
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                //m(把下标当作m)的父节点是n 并查集的合并
                ends[m] = n;
                result[index++] = edges[i];
            }
        }
        System.out.println(Arrays.toString(result));
    }

    public static void sort(Side[] sides) {
        Side temp;
        for (int i = 1; i < sides.length; i++) {
            for (int j = 0; j < sides.length - i; j++) {
                if (sides[j].weight > sides[j + 1].weight) {
                    temp = sides[j];
                    sides[j] = sides[j + 1];
                    sides[j + 1] = temp;
                }
            }
        }
    }

    public static Side[] getEdges() {
        Side[] sides = new Side[edgeNums];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    sides[index++] = new Side(vertx[i], vertx[j], matrix[i][j]);
                }
            }
        }
        return sides;
    }

    /**
     * 拿到顶点的位置
     *
     * @param ch c
     * @return
     */
    private static int getPosition(char ch) {
        for (int i = 0; i < vertx.length; i++) {
            if (vertx[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private static int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private static String add(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] result = new int[Math.max(c1.length, c2.length) + 1];
        int[] n1 = new int[c1.length];
        int[] n2 = new int[c2.length];
        for (int i = 0; i < n1.length; i++) {
            n1[i] = c1[i] - '0';
        }
        for (int i = 0; i < n2.length; i++) {
            n2[i] = c2[i] - '0';
        }
        if (n1.length == n2.length) {
            for (int i = n1.length - 1; i >= 0; i--) {
                result[i + 1] = n1[i] + n2[i];
            }
        } else if (n1.length > n2.length) {
            add(result, n2, n1);
        } else {
            add(result, n1, n2);
        }
        for (int i = result.length - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i == 0 && result[i] == 0) {
                continue;
            }
            builder.append(result[i]);
        }
        return builder.toString();
    }

    public static void add(int[] result, int[] n1, int[] n2) {
        int len = n2.length - 1;
        int l = result.length - 1;
        for (int i = n1.length - 1; i >= 0; i--) {
            result[l--] = n2[len--] + n1[i];
        }
        for (int i = len; i >= 0; i--) {
            result[l--] = n2[i];
        }
    }

    /**
     * 一个对象表示一条边
     */
    static class Side {
        /**
         * 开始点
         */
        char start;

        /**
         * 终点
         */
        char end;

        /**
         * 边的长度
         */
        int weight;

        public Side(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "<" + start + ", " + end + "> len=" + weight;
        }
    }
}
