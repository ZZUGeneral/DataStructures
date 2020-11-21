package com.yhl.dynamic;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName KnapsackProblem
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/2 21:30
 * @Version 1.0
 * @Description TODO
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3}; // 重量
        int[] value = {1500, 3000, 2000}; // 价值
        int m = 4;// 背包的容量`

        System.out.println(knapSack1(weight, value, m));
    }

    public static int knapSack1(int[] weight, int[] value, int m) {
        int len = value.length;
        int[][] res = new int[len + 1][m + 1];
        for (int i = 0; i <= len; i++) res[i][0] = 0;
        for (int i = 0; i < m; i++) res[0][i] = 0;
       
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= m; j++) {
                if (weight[i - 1] > j) res[i][j] = res[i - 1][j];
                else {
                    res[i][j] = Math.max(res[i - 1][j], value[i-1] + res[i - 1][j - weight[i - 1]]);
                }
            }
        }
        return res[len][m];
    }

    public static int knapSack2(int[] w, int[] v, int C) {
        int size = w.length;
        if (size == 0) {
            return 0;
        }

        int[] dp = new int[C + 1];
        //初始化第一行
        //仅考虑容量为C的背包放第0个物品的情况
        for (int i = 0; i <= C; i++) {
            dp[i] = w[0] <= i ? v[0] : 0;
        }

        for (int i = 1; i < size; i++) {
            for (int j = C; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[C];
    }
}
