package com.zhu.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zpm
 * @version 1.0
 */
public class Graph {
    private final List<String> vertex;
    private final int[][] edges;
    private final boolean[] isVisited;
    private final LinkedList<Integer> queue = new LinkedList<>();
    /**
     * 边的数目
     */
    private int edgesNum;

    public Graph(int n) {
        edges = new int[n][n];
        vertex = new ArrayList<>();
        isVisited = new boolean[n];
    }

    public static void main(String[] args) {
        int num = 5;
        String[] vertex = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(num);
        for (String value : vertex) {
            graph.insertVertex(value);
        }
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.showGraph();

        graph.bfs();
    }

    public void bfs() {
        for (int i = 0; i < vertex.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    private void bfs(boolean[] isVisited, int index) {
        //当前下标
        int now;
        //下一个下标
        int next;
        System.out.print(vertex.get(index) + "->");
        isVisited[index] = true;
        //访问过就直接入队,为了方便从A找不到其它然后取出访问过的其他路径在访问
        queue.addLast(index);
        while (!queue.isEmpty()) {
            //拿到最先入队的顶点尝试通往其他路径
            now = queue.removeFirst();
            //得到第一个从上一个顶点可以到达的第一个顶点
            next = getFirstNeighbor(now);
            while (next != -1) {
                //如果没有被访问直接访问然后如堆
                if (!isVisited[next]) {
                    System.out.print(vertex.get(next) + "->");
                    isVisited[next] = true;
                    queue.addLast(next);
                } else {
                    //如果被访问了尝试从同一个顶点走下一个顶点看看是不是可达,如果不可达在从队列中取出另外一个顶点
                    next = getNextNeighbor(now, next);
                }
            }
        }
    }

    private void dfs(boolean[] isVisited, int index) {
        //访问该节点,输出
        System.out.println(vertex.get(index) + "->");
        //将访问过的节点都置为访问过
        isVisited[index] = true;
        //查找i节点的第一个邻接点
        int firstNeighbor = getFirstNeighbor(index);
        while (firstNeighbor != -1) {
            if (!isVisited[firstNeighbor]) {
                dfs(isVisited, firstNeighbor);
            } else {
                firstNeighbor = getNextNeighbor(index, firstNeighbor);
            }
        }
    }

    public void dfs() {
        for (int i = 0; i < vertex.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 得到第一个零界点的下标
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertex.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 返回下一个临界点的下标
     *
     * @param v1 行
     * @param v2 列
     * @return
     */
    private int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertex.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void insertVertex(String vertex) {
        this.vertex.add(vertex);
    }

    /**
     * 关联边
     *
     * @param v1     A顶点
     * @param v2     B顶点
     * @param weight 连通或者不连通
     */
    public void insertEdges(int v1, int v2, int weight) {
        //由于是无向的所以是互相连通
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgesNum++;
    }

    public int getEdgesNum() {
        return edgesNum;
    }

    public int getVertex() {
        return vertex.size();
    }

    public void showGraph() {
        for (int[] vv : edges) {
            System.out.println(Arrays.toString(vv));
        }
    }
}
