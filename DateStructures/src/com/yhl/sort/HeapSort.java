package com.yhl.sort;

import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HeapSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/22 21:18
 * @Version 1.0
 * @Description TODO
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 6, 3, 1, 45, 65, 20};
        System.out.println("堆排序:");
        heapSort(arr);

    }

    public static void heapSort(int arr[]) {

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            addJustHeap(arr, i, arr.length);
        }
        int tmp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            tmp = arr[j];
            arr[j] = arr[0];
            arr[0] = tmp;
            addJustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void addJustHeap(int arr[], int i, int len) {
        int tmp = arr[i];
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                k++;
            }

            if (arr[k] > tmp) {
                tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
                i = k;
            } else {
                break;
            }
        }
    }
}
