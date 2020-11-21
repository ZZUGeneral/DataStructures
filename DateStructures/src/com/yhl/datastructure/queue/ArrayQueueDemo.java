package com.yhl.datastructure.queue;

import java.util.Scanner;

/**
 * @author YHL
 * @title: ArrayQueueDemo
 * @projectName DataStructures
 * @description: TODO
 * @date 2020/8/215:51
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(5);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获取数据");
            System.out.println("h(head):查看头部");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入数据:");
                    int val = scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("获取的数据是:%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("首位的数据是:%d\n", head);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("运行结束！");
                default:
                    break;
            }


        }

    }

    // 使用数组模拟队列，编写一个ArrayQueue类
    static class ArrayQueue {
        private int maxSize;//数组最大容量
        private int front;//队列头
        private int rear;//队列尾
        private int[] arr;//存放数据，模拟队列

        //创建队列的构造器
        public ArrayQueue(int arrayMaxSize) {
            maxSize = arrayMaxSize;
            arr = new int[maxSize];
            front = -1;
            rear = -1;
        }

        //判断队列是否满
        private boolean isFull() {
            return rear == maxSize - 1;
        }

        //判断队列是否为空
        private boolean isEmpty() {
            return rear == front;
        }

        //添加数据
        private void addQueue(int num) {
            if (isFull()) {
                System.out.println("队列已满，不能添加数据！");
                return;
            }
            rear++;
            arr[rear] = num;
        }

        // 获取数据，移除队列
        private int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，无法获取数据！");
            }
            front++;
            return arr[front];
        }

        //显示队列所有数据
        private void showQueue() {
            //遍历
            if (isEmpty()) {
                System.out.println("队列为空，请添加数据！");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("queue[%d]=%d\n", i, arr[i]);
            }
        }

        //显示队列的头数据
        private int headQueue() {
            if (isEmpty()) {
                System.out.println("队列为空，请添加数据！");
                return 0;
            }
            return arr[front + 1];
        }

    }

}

