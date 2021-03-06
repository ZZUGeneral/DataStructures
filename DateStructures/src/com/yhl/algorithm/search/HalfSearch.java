package com.yhl.algorithm.search;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HalfSearch
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/29 22:16
 * @Version 1.0
 * @Description 二分查找（数组必须有序）
 */
public class HalfSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 15, 16, 20, 35};
        int index = halfSearch(arr, 0, arr.length - 1, 14);
        if (index == -1) {
            System.out.println("没有找到该值！");
        }
        System.out.println("找到该值，下标为：" + index);

    }

    public static int halfSearch(int[] arr, int left, int right, int value) {
        // left > right 时，没有找到该值，返回 -1
        System.out.println("======TIME=====");
        if (left > right || value < arr[left] || value > arr[right]) return -1;
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        // int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (value > midValue) {
            return halfSearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            return halfSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    public static int halfSearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
