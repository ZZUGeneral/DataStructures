package com.yhl.datastructure.linkedlist;/**
 * @title: DOubleLinkedList
 * @projectName DataStructures
 * @description: TODO
 * @author YHL
 * @date 2020/8/1021:49
 */

/**
 * @ClassName DoubleLinkedList
 * @Description TODO
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/8/10 21:49
 * @Version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.addDoubleHeroByOrder(hero2);
        list.addDoubleHeroByOrder(hero1);
        list.addDoubleHeroByOrder(hero4);
        list.addDoubleHeroByOrder(hero3);
        list.showLinkedList();

        //修改节点
        System.out.println("双向链表修改节点后:");
        DoubleHeroNode hero2_1 = new DoubleHeroNode(2, "卢俊义", "玉麒麟---");
        list.updateByNo(hero2_1);
        list.showLinkedList();

        //删除节点
        System.out.println("双向链表删除节点后:");
        list.deleteHeroNode(2);
        list.showLinkedList();

        //统计链表有效长度
        System.out.println("双向链表有效节点数量=" + list.getLength());

        //查找倒数第2个节点
        System.out.println("双向链表倒数第2个节点是" + list.getNodeBackNum(2));

    }
}

//定义DoubleLinkedList 管理英雄
class DoubleLinkedList {
    //先初始化一个头结点,不存放数据
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //找到最后的节点，将最后的节点的next指向添加的节点
    public void addDoubleHeroNode(DoubleHeroNode doubleHeroNode) {
        // head节点不能动，所以需要一个辅助变量
        DoubleHeroNode temp = head;
        while (true) {
            //判断是否是最后一个节点
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = doubleHeroNode;
        doubleHeroNode.pre = temp;
    }

    //根据排名插入英雄
    public void addDoubleHeroByOrder(DoubleHeroNode heroNode) {
        DoubleHeroNode temp = head;
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
            heroNode.pre = temp;
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
        }

    }

    //根据编号修改信息
    public void updateByNo(DoubleHeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        //找到需要修改的节点
        DoubleHeroNode temp = head.next;
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
        DoubleHeroNode temp = head;
        if (temp.next == null) {
            System.out.println("链表为空！");
            return;
        }
        temp = temp.next;
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

        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果这是最后一个节点，会出现空指针错误
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }


    //查找单链表中倒数第K个节点
    public DoubleHeroNode getNodeBackNum(int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength();
        if (index <= 0 || index > size) {
            return null;
        }
        DoubleHeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


}

//定义DoubleHeroNode,每个DoubleHeroNode对象就是一个节点
class DoubleHeroNode {
    public int no;
    public String name;
    public String nickName;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //重写toString方法，美化显示
    @Override
    public String toString() {
        return "DoubleHeroNode[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                "]";
    }
}
