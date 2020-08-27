package com.zhu.algorithms.floyd;

/**
 * @author zpm
 * @version 1.0
 */
public class Floyd {
    private final static int N = Integer.MAX_VALUE;
    /**
     * 顶点数组
     */
    private static char[] mVexs;

    /**
     * 邻接矩阵
     */
    private static int[][] mMatrix;

    static {
        mVexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        mMatrix = new int[][]{
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
        char[][] path = new char[mVexs.length][mVexs.length];
        int[][] dist = new int[mVexs.length][mVexs.length];
        floyd(path, dist);
    }

    /**
     * floyd最短路径。
     * 即，统计图中各个顶点间的最短路径。
     * <p>
     * 参数说明：
     *
     * @param path 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
     * @param dist 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
     */
    public static void floyd(char[][] path, int[][] dist) {

        // 初始化
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                dist[i][j] = mMatrix[i][j];
                // "顶点i"到"顶点j"的最短路径是经过顶点j。
                path[i][j] = mVexs[j];
            }
        }

        // 计算最短路径
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k] == N || dist[k][j] == N) ? N : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        // 打印floyd最短路径的结果
        System.out.println("floyd: ");
        System.out.println("   A   B   C   D   E   F   G");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.print(mVexs[i] + " ");
            for (int j = 0; j < mVexs.length; j++) {
                System.out.printf("%2d  ", dist[i][j]);
            }
            System.out.println();
        }

        System.out.println("path: ");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                System.out.print("    "+ path[i][j]);
            }
            System.out.println();
        }
    }
}
