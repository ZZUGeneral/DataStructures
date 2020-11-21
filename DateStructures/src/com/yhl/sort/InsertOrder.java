package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName InsertOrder
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/9 20:49
 * @Version 1.0
 * @Description 插入排序
 */
public class InsertOrder {
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

        insertOrder(arr);

        Date date2 = new Date();
        String date2Str = dateFormat.format(date2);
        System.out.println("排序后时间：" + date2Str);

        System.out.print("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertOrder(int[] arr) {
        //待插入数
        int insertVal = 0;
        //待插入数下标
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //判断是否需要赋值
            if (insertIndex != i - 1)
                arr[insertIndex + 1] = insertVal;

//            System.out.printf("第%d轮排序后：", i + 1);
//            System.out.println(Arrays.toString(arr));
        }

    }
}
