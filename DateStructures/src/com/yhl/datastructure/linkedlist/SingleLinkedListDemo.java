package com.yhl.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author YHL
 * @title: SingleLinkedList
 * @projectName DataStructures
 * @description: TODO
 * @date 2020/8/322:25
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();
        list.addHeroByOrder(hero1);
        list.addHeroByOrder(hero4);
        list.addHeroByOrder(hero3);
        list.addHeroByOrder(hero2);
        list.showLinkedList();

        //修改节点
        System.out.println("修改节点后:");
        HeroNode hero2_1 = new HeroNode(2, "卢俊义", "玉麒麟---");
        list.updateByNo(hero2_1);
        list.showLinkedList();

        //删除节点
        System.out.println("删除节点后:");
        list.deleteHeroNode(2);
        list.showLinkedList();

        //统计链表有效长度
        System.out.println("有效节点数量=" + list.getLength());

        //查找倒数第2个节点
        System.out.println("倒数第2个节点是" + list.getNodeBackNum(2));

        //翻转链表
        System.out.println("翻转链表后:");
        list.reverseList();
        list.showLinkedList();

        //逆序打印链表
        System.out.println("逆序打印链表:");
        list.reversePrint();
        list.reverseList();
        //创建节点
        HeroNode hero5 = new HeroNode(5, "宋江5", "及时雨");
        HeroNode hero6 = new HeroNode(6, "卢俊义6", "玉麒麟");
        HeroNode hero7 = new HeroNode(7, "吴用7", "智多星");
        HeroNode hero8 = new HeroNode(8, "林冲8", "豹子头");

        SingleLinkedList list2 = new SingleLinkedList();
        list.addHeroByOrder(hero1);
        list.addHeroByOrder(hero4);
        list.addHeroByOrder(hero6);
        list.addHeroByOrder(hero7);
        /** 如果不适用new，直接使用hero1，在执行过程中，由于hero1已经被修改，执行会报错。
         * 这里的函数参数是指针，在执行过程中，hero1.next不断修改，中间如果修改可能会成为环
         **/

        list2.addHeroByOrder(new HeroNode(8, "林冲8", "豹子头"));
        list2.addHeroByOrder(new HeroNode(5, "宋江5", "及时雨"));
        list2.addHeroByOrder(new HeroNode(6, "卢俊义6", "玉麒麟"));
        list2.addHeroByOrder(new HeroNode(3, "吴用", "智多星"));
        list2.addHeroByOrder(new HeroNode(1, "宋江", "及时雨"));

        System.out.println("第一个链表：");
        list.showLinkedList();

        System.out.println("第二个链表：");
        list2.showLinkedList();

        System.out.println("两个有序链表合并后：");
        SingleLinkedList list3 = twoListByOrder(list, list2);
        list3.showLinkedList();

    }

    public static SingleLinkedList twoListByOrder(SingleLinkedList list1, SingleLinkedList list2) {
        SingleLinkedList list = new SingleLinkedList();
        HeroNode temp1 = list1.getHead().next;
        HeroNode temp2 = list2.getHead().next;
        HeroNode head = list.getHead();
        while (temp1 != null && temp2 != null) {
            if (temp1.no > temp2.no) {
                head.next = temp2;
                temp2 = temp2.next;
            } else if (temp1.no < temp2.no) {
                head.next = temp1;
                temp1 = temp1.next;
            } else {
                head.next = temp1;
                temp2 = temp2.next;
                temp1 = temp1.next;
            }
            head = head.next;
        }
        HeroNode temp = null;
        if (temp1 == null) {
            temp = temp2;
        }
        if (temp2 == null) {
            temp = temp1;
        }
        while (temp != null) {
            head.next = temp;
            temp = temp.next;
            head = head.next;
        }
        return list;

    }

}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {
    //先初始化一个头结点,不存放数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //找到最后的节点，将最后的节点的next指向添加的节点
    public void addHeroNode(HeroNode heroNode) {
        // head节点不能动，所以需要一个辅助变量
        HeroNode temp = head;
        while (true) {
            //判断是否是最后一个节点
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //根据排名插入英雄
    public void addHeroByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            System.out.printf("待插入的英雄的编号%d已存在,插入失败!\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //根据编号修改信息
    public void updateByNo(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到该编号围殴%d节点,修改失败!\n", newHeroNode.no);
        }
    }

    //显示链表
    public void showLinkedList() {

        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            //判断是否是最后一个节点
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //删除节点
    public void deleteHeroNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除！");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到该编号围殴%d节点,删除失败!\n", no);
        }
    }

    //统计单链表节点的个数（不统计头结点）
    public int getLength() {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表中倒数第K个节点

    public HeroNode getNodeBackNum(int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength();
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //翻转单链表
    public void reverseList() {
        //链表为空或只有一个节点是，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//当前节点的下一个节点

        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;

        }
        head.next = reverseHead.next;
        head = reverseHead;
    }

    //逆序打印链表
    public void reversePrint() {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


}


//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //重写toString方法，美化显示
    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                "]";
    }
}
