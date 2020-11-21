package com.yhl.datastructure.hashtable;

import com.yhl.recursion.Test;

import java.beans.beancontext.BeanContext;
import java.util.Scanner;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HashTableDemo
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/17 22:02
 * @Version 1.0
 * @Description TODO
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable table = new HashTable(8);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("====== MENU ======");
            System.out.println(" add : 添加雇员");
            System.out.println(" find : 查找雇员");
            System.out.println(" list : 显示雇员");
            System.out.println(" exit : 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入 ID : ");
                    int id = scanner.nextInt();
                    System.out.println("请输入名称 : ");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    table.add(emp);
                    break;
                case "find":
                    System.out.println("请输入查找雇员 ID : ");
                    int findID = scanner.nextInt();
                    table.find(findID);
                    break;
                case "list":
                    table.list();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }


    }
}

// hash 表
class HashTable {
    private EmpLinkedList[] empLinkedListArray = null;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void add(Emp emp) {
        // 根据 ID 确定 hash 值
        int index = hashCode(emp.id);

        // 执行添加
        empLinkedListArray[index].add(emp);

    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    public void find(int id) {
        int index = hashCode(id);
        Emp emp = empLinkedListArray[index].find(id);
        if (emp != null) {
            System.out.printf("在第 %d 条链表找到该数据，id=%d   name=%s\n", index, emp.id, emp.name);
        } else {
            System.out.println("没有找到该数据！");
        }
    }

    public int hashCode(int id) {
        return id % size;
    }

}

// 雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

// 链表
class EmpLinkedList {
    // 头节点
    private Emp head;

    // 添加雇员
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        // 辅助指针
        Emp tmp = head;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        // 添加雇员
        tmp.next = emp;
    }

    // 遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空！");
            return;
        }
        System.out.println("第" + no + "条链表信息如下 : ");
        Emp tmp = head;
        while (true) {
            System.out.printf("===> id=%d name=%s\n", tmp.id, tmp.name);
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        System.out.println("第" + no + "条链表信息打印完毕！");
    }

    public Emp find(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        Emp tmp = head;
        while (true) {
            if (tmp.id == id) {
                break;
            }

            // 遍历到 null 表示没有找到数据
            if (tmp.next == null) {
                tmp = null;
                break;
            }
            tmp = tmp.next;
        }
        return tmp;
    }
}
