package com.yhl.algorithm.sort;

import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName QuickSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/11 21:23
 * @Version 1.0
 * @Description 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left, r = right; //左右下标
        //中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        // while循环的目的是让比 pivot 小的值放到左边
        while (l < r) {
            // 在 pivot 左边一直找，直到找到大于等于 pivot的值才退出
            while (arr[l] < pivot) {
                l = l + 1;
            }
            // 在 pivot 右边一直找，直到找到小于等于 pivot的值才推出
            while (arr[r] > pivot) {
                r = r - 1;
            }
            // l>=r 说明pivot的左右两边的值，已经按照左边全是小于等于pivot的值，右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完毕，发现 arr[l] == pivot 值相等 r--，前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完毕，发现 arr[r] == pivot 值相等 l++, 后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 如果 l==r,必须使 l++,r--,否则为出现栈溢出
        if (l == r) {
            l = l + 1;
            r = r - 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }
}
