package com.yhl.datastructure.Josepfu;

/**
 * @author YHL
 * @title: Josefu
 * @projectName DataStructures
 * @description: TODO
 * @date 2020/8/1822:03
 */
public class Josefu {
    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        list.addBoy(25);
        list.showBoy();
        list.countBOy(10, 5, 25);

    }
}

//创建单向环形链表
class CircleLinkedList {
    //创建first节点
    private Boy first = null;

    //添加小孩节点，构建环形链表
    public void addBoy(int num) {
        //数据校验num
        if (num < 1) {
            System.out.println("num数量错误！");
            return;
        }
        Boy cur = null; //辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= num; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成环
                cur = first; //cur 指向第一个
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    //遍历链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", cur.getNo());
            if (cur.getNext() == first) { //遍历结束
                break;
            }
            cur = cur.getNext(); //boy后移
        }
    }

    //根据用户输入，计算小孩出圈顺序

    /**
     * @param startNo 表示从第几个小孩开始数数, countNum，数几下, num有多少小孩
     * @return void
     * @throws
     * @description TODO
     * @author YHL
     * @date 2020/8/18 22:23
     */
    public void countBOy(int startNo, int countNum, int num) {
        //数据校验
        if (first == null || startNo < 1 || startNo > num) {
            System.out.println("数据有误，请重新输入！");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) { //遍历结束
                break;
            }
            helper = helper.getNext(); //boy后移
        }
        //移动到开始数数的孩子位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数
        while (true) {
            if (helper == first) { //链表中只有一个节点
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向出圈小孩
            System.out.printf("小孩%d出圈！！！\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后在圈中的小孩%d！！！\n", first.getNo());
    }


}

class Boy {
    private int no; //编号
    private Boy next; //下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
