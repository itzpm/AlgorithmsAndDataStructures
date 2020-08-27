package com.zhu.algorithms.dijkstra;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class Dijkstra {
    private final static int N = Integer.MAX_VALUE;
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
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N},
        };
    }

    public static void main(String[] args) {
        int[] dist = new int[vertx.length];
        int[] pre = new int[vertx.length];
        for(int i = 0;i< vertx.length;i++){
            dijkstra(i,pre,dist);
        }
    }

    /**
     * Dijkstra最短路径。
     * 即，统计图中"顶点vs"到其它各个顶点的最短路径。
     * <p>
     *
     * @param vs   起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
     * @param prev 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
     * @param dist 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
     *             </p>
     */
    public static void dijkstra(int vs,int[] prev, int[] dist) {
        // flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获取
        boolean[] flag = new boolean[vertx.length];

        // 初始化
        // 顶点i的最短路径为"顶点vs"到"顶点i"的权。
        System.arraycopy(matrix[vs], 0, dist, 0, vertx.length);
        Arrays.fill(prev,0);
        // 对"顶点vs"自身进行初始化
        flag[vs] = true;
        //自己到自己距离为0
        dist[vs] = 0;

        // 遍历mVexs.length-1次；每次找出一个顶点的最短路径。
        int k = 0;
        for (int i = 1; i < vertx.length; i++) {
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
            int min = N;
            for (int j = 0; j < vertx.length; j++) {
                if (!flag[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            // 标记"顶点k"为已经获取到最短路径
            flag[k] = true;

            // 修正当前最短路径和前驱顶点
            // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
            for (int j = 0; j < vertx.length; j++) {
                int tmp = (matrix[k][j] == N ? N : (min + matrix[k][j]));
                if (!flag[j] && tmp < dist[j]) {
                    dist[j] = tmp;
                    prev[j] = k;
                }
            }
        }

        // 打印dijkstra最短路径的结果
        System.out.printf("dijkstra(%c): \n", vertx[vs]);
        for (int i = 0; i < vertx.length; i++) {
            if (vertx[vs] == vertx[i]) {
                continue;
            }
            System.out.printf("  shortest(%c, %c)=%d\n", vertx[vs], vertx[i], dist[i]);
        }
        System.out.println();
    }
}
