package com.yhl.algorithm.recursion;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName Maze
 * @Description TODO
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/8 11:11
 * @Version 1.0
 */
public class Maze {
    public static void main(String[] args) {
        //二维数组模拟迷宫
        int[][] maze = new int[8][7];
        //使用 1 表示墙
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[3][5] = 1;
        maze[4][2] = 1;
        maze[6][4] = 1;
        maze[4][4] = 1;
        System.out.println("输出迷宫");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯小球路径
        boolean f = setWay(maze, 1, 1);
        System.out.println("输出新迷宫");
        //输出新的地图：小球走过并表示过的地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * @param maze, i, j]
     * @return boolean
     * @throws
     * @description i, j表示起始位置
     * maze[i][j] 为 0 表示没走过，1 表示墙，2表示通路可以走，3表示该店走过，但是走不通
     * @author YHL
     * @date 2020/10/8 12:24
     */
    public static boolean setWay(int[][] maze, int i, int j) {
        if (maze[6][5] == 2) {
            //找到通路
            return true;
        } else if (maze[i][j] == 0) {
            //该点没有走过，按下->右->上->左 走
            maze[i][j] = 2;
            if (setWay(maze, i + 1, j)) {
                return true;
            } else if (setWay(maze, i, j + 1)) {
                return true;
            } else if (setWay(maze, i - 1, j)) {
                return true;
            } else if (setWay(maze, i, j - 1)) {
                return true;
            } else {
                maze[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
