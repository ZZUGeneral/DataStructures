package com.yhl.datastructure.stack;/**
 * @title: StackDemo
 * @projectName DataStructures
 * @description: TODO
 * @author YHL
 * @date 2020/8/2322:00
 */

import java.util.Scanner;

/**
 * @ClassName StackDemo
 * @Description TODO
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/8/23 22:00
 * @Version 1.0
 */
public class StackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        int key = 0;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("1. show显示栈");
            System.out.println("2. exit退出栈");
            System.out.println("3. push添加元素");
            System.out.println("4. pop取出元素");
            System.out.println("请选择：");
            key = scanner.nextInt();
            switch (key) {
                case 1:
                    stack.show();
                    break;
                case 2:
                    scanner.close();
                    loop = false;
                    break;
                case 3:
                    System.out.println("请输入添加的元素：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 4:
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据：" + res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出，运行结束！");

    }
}

class ArrayStack {
    private int maxSize; //大小
    private int[] stack; //数组模拟栈存储数据
    private int top = -1; //表示栈顶，初始化为 -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满！！！");
            return;
        }
        top++;
        stack[top] = value;

    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！！！");
        }
        int value = stack[top];
        top --;
        System.out.println("top="+top);
        return value;
    }

    //显示栈情况
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空！！！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
