package com.yhl.sort;

import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ShellSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/9 21:44
 * @Version 1.0
 * @Description TODO
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.print("排序前：");
        System.out.println(Arrays.toString(arr));

        shellSort2(arr);

        System.out.print("排序后：");
        System.out.println(Arrays.toString(arr));


    }

    //使用逐步推导来编写希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            // 数据分组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素
                for (int j = i - gap; j >= 0; j = j - gap) {
                    //如果当前元素大于步长后的元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }

                }
            }
            System.out.println("第" + (++count) + "轮排序后：" + Arrays.toString(arr));
        }

    }

    //移位法--- 对交换式希尔排序进行优化
    public static void shellSort2(int[] arr) {
        // 增量 gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            //从第 gap 个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    // 找到插入位置
                    arr[j] = temp;
                }
            }
        }
    }
}
