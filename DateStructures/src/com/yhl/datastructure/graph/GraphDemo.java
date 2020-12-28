package com.yhl.datastructure.graph;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName GraphDemo
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/24 20:45
 * @Version 1.0
 * @Description 图
 */
public class GraphDemo {
    private ArrayList<String> vertexList; // 顶点集合
    private int[][] edges; // 邻接矩阵
    private int numOfEdges; // 边的数量
    private boolean[] visited;

    public static void main(String[] args) {
        int n = 8;
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建对象
        GraphDemo graph = new GraphDemo(n);
        // 添加节点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        System.out.println("显示邻接矩阵：");
        graph.showGraph();

        System.out.println("深度优先：");
        graph.dfs();

        System.out.println("广度优先：");
        graph.bfs();


    }

    // 深度优先
    public void dfs(boolean[] isVisited, int index) {
        System.out.print(getValueByIndex(index) + "->");
        isVisited[index] = true;
        int w = getFirstNeighbor(index);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    public void dfs() {
        visited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!visited[i]) {
                dfs(visited, i);
            }
        }
    }

    // 广度优先
    public void bfs(boolean[] isVisited, int index) {
        int u; // 头结点对应下标
        int w;// 邻接节点
        // 记录访问节点顺序
        LinkedList queue = new LinkedList();
        // 访问节点，打印信息
        System.out.print(getValueByIndex(index) + "->");
        // 已访问
        isVisited[index] = true;
        // 加入访问队列
        queue.addLast(index);

        while (!queue.isEmpty()) {
            // 取出头结点下标
            u = (Integer) queue.removeFirst();
            // 第一个邻接节点下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                // 下一个邻接节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        visited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!visited[i]) {
                bfs(visited, i);
            }
        }
    }


    public GraphDemo(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    // 返回第一个邻接点
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 返回节点数量
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 显示邻接矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点对应的数据
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    // 返回两点之间的边的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }


    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
