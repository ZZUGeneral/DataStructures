package com.yhl.recursion;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName EightQueue
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/8 13:40
 * @Version 1.0
 * @Description TODO
 */
public class EightQueue {
    //皇后的数量
    int max = 8;
    //定义数组表示皇后放置位置的结果
    int[] array = new int[max];

    //解法数量
    static int count = 0;
    static int judegCount = 0;

    public static void main(String[] args) {
        EightQueue queue = new EightQueue();
        queue.check(0);
        System.out.printf("一共有%d种解法！\n", count);
        System.out.printf("一共有%d次冲突判断！\n", judegCount);
    }

    //放置第 n 个皇后
    public void check(int n) {
        if (n == max) {
            printQueue();
            return;
        }
        // 依次放置皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //当前皇后应该放置在第几列
            array[n] = i;
            //放置当前皇后到 i 列时，是否冲突
            if (judge(n)) {
                //不冲突，放置下一个皇后
                check(n + 1);
            }
            //冲突，当前皇后移动到下一列
        }
    }

    //当放置第 n 个皇后是，检测该皇后的位置是否和已摆放的皇后冲突
    public boolean judge(int n) {
        judegCount++;
        //array[i] == array[n] 判断两个皇后是否在同一列
        //Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断两个皇后是否在同一斜线上
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) return false;
        }
        return true;

    }
    // 将皇后的位置打印出来

    public void printQueue() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }
}
