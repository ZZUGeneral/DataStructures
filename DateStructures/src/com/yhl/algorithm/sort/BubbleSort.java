package com.yhl.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName BubbleSort
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/8 21:52
 * @Version 1.0
 * @Description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};

//        System.out.print("排序前：");
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        String date1Str = dateFormat.format(date1);
        System.out.println("排序前时间：" + date1Str);

        //测试冒泡排序
        bubbleSortSecond(arr);

        Date date2 = new Date();
        String date2Str = dateFormat.format(date2);
        System.out.println("排序后时间：" + date2Str);

        System.out.print("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSortFirst(int[] arr) {
        //换位时的临时变量
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                //如果前面的数比后面的数大就进行交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.printf("第%d趟排序结果：", i);
//            System.out.println(Arrays.toString(arr));

        }
    }


    public static void bubbleSortSecond(int[] arr) {
        //换位时的临时变量
        int temp = 0;
        //标志位,表示是否进行了交换
        boolean flag = false;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                //如果前面的数比后面的数大就进行交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            //如果一轮排序后没有位置变换，说明排序完成
//            System.out.printf("第%d趟排序结果：", i);
//            System.out.println(Arrays.toString(arr));
            if (!flag) break;
            else flag = false;
        }
    }
}
