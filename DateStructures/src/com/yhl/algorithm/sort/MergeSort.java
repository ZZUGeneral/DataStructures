package com.yhl.algorithm.sort;

import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName MergeSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/18 21:55
 * @Version 1.0
 * @Description 归并排序 : 空间换时间
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);

        System.out.println("归并排序后：");
        System.out.println(Arrays.toString(arr));
    }

    // 分隔方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 左递归
            mergeSort(arr, left, mid, temp);
            // 右递归
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }

    }

    // 合并方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 左边有序序列的初始索引
        int j = mid + 1;// 右边有序序列的初始索引
        int t = 0;// temp 临时数组的当前索引

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t = t + 1;
                i = i + 1;
            } else {
                temp[t] = arr[j];
                t = t + 1;
                j = j + 1;
            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t = t + 1;
            i = i + 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t = t + 1;
            j = j + 1;
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t = t + 1;
            tempLeft = tempLeft + 1;
        }

    }
}
