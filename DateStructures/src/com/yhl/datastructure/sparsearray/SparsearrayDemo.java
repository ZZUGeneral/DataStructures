package com.yhl.datastructure.sparsearray;

/**
 * @author YHL
 * @title: sparsearray
 * @projectName DataStructures
 * @description: TODO
 * @date 2020/7/3022:07
 */
public class SparsearrayDemo {
    public static void main(String[] args) {
        //创建一个二维数组表示棋盘，1表示黑子，2表示白子，0表示无子
        int[][] chessArr = new int[11][11];
        chessArr[1][1] = 1;
        chessArr[3][5] = 2;

        //将二维数组转换为稀疏数组
        //先遍历二维数组，获取数据个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建对应的二维数组
        int sparseArr[][] = new int[sum + 1][3];
        //稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将数组存入稀疏数组
        int index = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr[i][j];
                }
            }
        }
        // 输出稀疏数组
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }

        //将稀疏数组恢复为二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }
    }
}
