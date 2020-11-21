package com.yhl.datastructure.stack;

import java.util.*;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName PolandExpression
 * @Description TODO
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/7 10:44
 * @Version 1.0
 */
public class PolandExpression {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        // (3+4)*5-6 ==>3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
        //先将 "3 4 + 5 * 6 -"放到ArrayList中作为参数传递到方法中
        List<String> rpList = getListString(suffixExpression);
        System.out.println("计算结果为：" + calculate(rpList));

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpression);
        System.out.println(parseSuffixExpressionList);
        System.out.println(expression + "=" + calculate(parseSuffixExpressionList));

    }

    //将一个逆波兰表达式，一次将数据和运算符放到ArrayList中
    public static List<String> getListString(String sufficExpression) {
        //将suffixExpression分隔
        String[] split = sufficExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    // 计算逆波兰表达式
    public static int calculate(List<String> ls) {
        // 创建栈
        Stack<String> stack = new Stack<String>();
        for (String str : ls) {
            // 使用正则表达式取出数
            if (str.matches("\\d+")) {
                //匹配的是多位数,直接入栈
                stack.push(str);
            } else {
                // 匹配的是运算符，取出两个数进行运算后入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (str.equals("+")) {
                    res = num1 + num2;
                } else if (str.equals("-")) {
                    res = num1 - num2;
                } else if (str.equals("*")) {
                    res = num1 * num2;
                } else if (str.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符号输入错误！");
                }
                stack.push(Integer.toString(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //中缀表达式转为对应的List
    // 1+((2+3)*4)-5 =>  1 2 3 + 4 * + 5 -
    public static List<String> toInfixExpression(String s) {
        //定义一个List，存储中缀表达式对应内容
        List<String> list = new ArrayList<>();
        int index = 0; // 遍历中缀表达式的的指针
        String str = new String(); //对多位数的拼接
        char ch = 'a'; // 遍历的字符
        do {
            // 如果 ch 是一个非数字，加入到ls
            if ((ch = s.charAt(index)) < 48 || (ch = s.charAt(index)) > 57) {
                list.add("" + ch);
                index++;
            } else {
                //如果是一个数字，需要考虑多位数的问题
                str = "";
                while (index < s.length() && (ch = s.charAt(index)) >= 48 && (ch = s.charAt(index)) <= 57) {
                    str += ch;
                    index++;
                }
                list.add(str);
            }

        } while (index < s.length());
        return list;
    }

    //中缀表达式的List转换为后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈，存储结果和中间结果
        Stack<String> s1 = new Stack<>();
        //整个操作过程中，结果栈s2没有pop操作，并且还要逆序输出，所以使用List代替
        //Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        //遍历list
        for (String str : list) {
            //如果是一个数，直接进栈s2
            if (str.matches("\\d+")) {
                s2.add(str);
            } else if (str.equals("(")) {
                s1.push(str);
            } else if (str.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符并压入s2，直至遇到左括号位置，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //当s1栈顶的运算符的优先级大于等于当前运算符，将s1的运算符弹出添加到s2
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(str)) {
                    s2.add(s1.pop());
                }
                s1.push(str);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//按顺序输出就是后缀表达式对应的逆波兰表达式
    }
}

//编写类 Operation，可以返回运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应优先级
    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "+/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符：" + operation);
                break;
        }
        return res;
    }
}