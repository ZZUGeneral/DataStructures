package com.yhl.dac;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HanoiTower
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/26 19:45
 * @Version 1.0
 * @Description TODO
 */
public class HanoiTower {
    public static void main(String[] args) {
        move(3, 'A', 'B', 'C');

    }

    public static void move(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第 1 个盘子 : " + a + "->" + c);
        } else {
            move(num - 1, a, c, b);
            System.out.println("第 " + num + " 个盘子 : " + a + "->" + c);
            move(num - 1, b, a, c);
        }
    }
}
