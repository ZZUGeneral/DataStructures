package com.yhl.datastructure.stack;/**
 * @title: Calsulator
 * @projectName DataStructures
 * @description: TODO
 * @author YHL
 * @date 2020/8/2521:12
 */

/**
 * @ClassName Calsulator
 * @Description ： 计算器
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/8/25 21:12
 * @Version 1.0
 */
public class Calsulator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        MyStack numStack = new MyStack(10);
        MyStack operStack = new MyStack(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = '*';
        String keepNum = "";
        //while循环扫描结束
        while (true) {
            //依次得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                //判断ch，做对应处理
                if (!operStack.isEmpty()) {
                    //处理
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入数栈
                        numStack.push(res);
                        //当前符号入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }

                } else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数字，入数栈
                //  numStack.push(ch - 48);

                // 处理多位数
                keepNum += ch;

                //ch已经是最后一位了
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //下一位是数字，继续扫描；运算符则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //下标后移
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //结果入数栈
            numStack.push(res);

        }
        System.out.println(expression + "=" + numStack.pop());
    }
}

//栈
class MyStack {
    private int maxSize; //大小
    private int[] stack; //数组模拟栈存储数据
    private int top = -1; //表示栈顶，初始化为 -1

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的值，并未出栈
    public int peek() {
        return stack[top];
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
        top--;
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

    //返回运算符优先级，数字越大，优先级越高
    public int priority(int oper) {
        // 假设目前只有加减乘除
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                // System.out.println(num1 + " + " + num2 + "=" + res);
                break;
            case '-':
                res = num2 - num1;
                // System.out.println(num2 + " - " + num1 + "=" + res);
                break;
            case '*':
                res = num1 * num2;
                // System.out.println(num1 + " * " + num2 + "=" + res);
                break;
            case '/':
                res = num2 / num1;
                // System.out.println(num2 + " / " + num1 + "=" + res);
                break;
            default:
                break;
        }

        return res;
    }

}

