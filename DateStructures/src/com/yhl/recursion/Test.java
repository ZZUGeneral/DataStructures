package com.yhl.recursion;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName Test
 * @Description TODO
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/8 9:21
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        test(9);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        else return factorial(n - 1) * n;
    }
}
