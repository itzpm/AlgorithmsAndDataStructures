package com.zhu.algorithms.prim;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class Prim {
    private static final int[][] WEIGHT;
    private static final char[] DATA;

    static {
        DATA = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int max = 100;
        WEIGHT = new int[][]{
                {max, 5, 7, max, max, max, 2},
                {5, max, max, 9, max, max, 3},
                {7, max, max, max, 8, max, max},
                {max, 9, max, max, max, 4, max},
                {max, max, 8, max, max, 5, 4},
                {max, max, max, 4, 5, max, 6},
                {2, 3, max, max, 4, 6, max},
        };
    }

    private final Mgraph mgraph = new Mgraph();

    public static void main(String[] args) {
        Prim prim = new Prim();
        prim.createGraph(DATA.length, DATA, WEIGHT);
        prim.show();
        prim.prim(1);
    }

    public void prim(int start) {
        int[] visited = new int[mgraph.vertx];
        visited[start] = 1;

        //h1 h2记录两个顶点的下标(访问时候的)
        int h1 = -1;
        int h2 = -1;
        //初始化最小路径的值
        int minWeight = 100;

        //这个for循环代表有n个顶点就会访问n-1次
        for (int k = 1; k < mgraph.vertx; k++) {
            //这个for循环代表已经访问过的顶点  用visited[i] == 1 表示
            //因为会从已经访问过的点中依次查看他们到达的点看那个更短
            for (int i = 0; i < mgraph.vertx; i++) {
                //这个for循环代表没访问过的顶点
                for (int j = 0; j < mgraph.vertx; j++) {
                    //因为需要找访问过的点和没有访问过的点之间的最小值
                    //所以 visited[i] == 0 没有访问过这个点直接看下一个点访问过没有
                    if (visited[i] == 0) {
                        break;
                    }
                    //如果visited[i] == 1 已经访问过那么就将minweight的值变为找到的最小的值
                    if (visited[i] == 1) {
                        if (visited[j] == 0 && mgraph.weight[i][j] < minWeight) {
                            minWeight = mgraph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
            }
            //当这两个for循环结束之后就会找到一个最小的边
            System.out.println("<" + mgraph.data[h1] + ", " + mgraph.data[h2] + "> 距离:" + minWeight);
            //将minWeight重置
            minWeight = 100;
            //h2对应的是 在访问过的边中到未访问边中的最小值
            visited[h2] = 1;
        }

    }

    public void createGraph(int vertx, char[] data, int[][] weight) {
        mgraph.data = data;
        mgraph.weight = weight;
        mgraph.vertx = data.length;
        for (int i = 0; i < vertx; i++) {
            mgraph.data[i] = data[i];
            System.arraycopy(weight[i], 0, mgraph.weight[i], 0, data.length);
        }
    }

    public void show() {
        for (int[] g : mgraph.weight) {
            System.out.println(Arrays.toString(g));
        }
    }
}
