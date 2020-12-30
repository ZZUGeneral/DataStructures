package com.yhl.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName SelectOrder
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/9 20:32
 * @Version 1.0
 * @Description 选择排序
 */
public class SelectOrder {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//
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

        selectOrderFirst(arr);

        Date date2 = new Date();
        String date2Str = dateFormat.format(date2);
        System.out.println("排序后时间：" + date2Str);

        System.out.print("排序后：");
        System.out.println(Arrays.toString(arr));

    }

    //选择排序
    public static void selectOrderFirst(int[] arr) {
        int index = 0;
        int min = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
//            System.out.printf("第%d轮排序后：", i + 1);
//            System.out.println(Arrays.toString(arr));
        }
    }
}
