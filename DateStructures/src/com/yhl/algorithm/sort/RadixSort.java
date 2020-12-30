package com.yhl.algorithm.sort;

import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName RadixSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/19 21:28
 * @Version 1.0
 * @Description TODO
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 14, 748};
        radixSort(arr);

    }

    // 基数排序
    public static void radixSort(int[] arr) {
        // 十个桶
        int[][] bucket = new int[10][arr.length];

        // 每个桶中数字数量
        int[] bucketElements = new int[10];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        int length = (max + "").length();
        for (int i = 0, n = 1; i < length; i++, n = n * 10) {
            max = max / 10;
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElements[digitOfElement]] = arr[j];
                bucketElements[digitOfElement]++;
            }
            int index = 0;
            // 将桶中数据按顺序取出
            for (int k = 0; k < bucketElements.length; k++) {
                for (int l = 0; l < bucketElements[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
                bucketElements[k] = 0;
            }

            System.out.println("第" + (i + 1) + "轮处理后：" + Arrays.toString(arr));
        }
    }
}
