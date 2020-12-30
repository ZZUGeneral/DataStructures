package com.yhl.algorithm.prim;

import java.util.Arrays;

public class PrimAlgotithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verx = data.length;
        //邻接矩阵
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5,  4,},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000,}};
        MyGraph myGraph = new MyGraph(verx);
        MinTree minTree = new MinTree();
        minTree.createGraph(myGraph, verx, data, weight);
        minTree.showMyGraph(myGraph);
        minTree.prim(myGraph, 0);

    }

}

class MinTree {
    public void createGraph(MyGraph myGraph, int verx, char[] data, int[][] weight) {
        for (int i = 0; i < verx; i++) {
            myGraph.data[i] = data[i];
            for (int j = 0; j < verx; j++) {
                myGraph.weight[i][j] = weight[i][j];
            }
        }

    }

    public void showMyGraph(MyGraph myGraph) {
        for (int[] link : myGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void prim(MyGraph myGraph, int v) {
        int[] visited = new int[myGraph.verx];
        visited[v] = 1;
        int min = 10000;
        int next = -1, cur = -1;
        for (int k = 1; k < myGraph.verx; k++) {
            for (int i = 0; i < myGraph.verx; i++) {
                for (int j = 0; j < myGraph.verx; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && min > myGraph.weight[i][j]) {
                        min = myGraph.weight[i][j];
                        next = j;
                        cur = i;
                    }
                }
            }
            System.out.println("<" + myGraph.data[cur] + "," + myGraph.data[next] + "> 权值: " + min);
            visited[next] = 1;
            min = 10000;
        }
    }
}

class MyGraph {
    int verx; // 节点数量
    char[] data; // 节点
    int[][] weight;// 邻接矩阵

    public MyGraph(int verx) {
        this.verx = verx;
        this.data = new char[verx];
        weight = new int[verx][verx];
    }
}
