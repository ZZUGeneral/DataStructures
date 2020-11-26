package com.yhl.datastructure.tree;

import javax.swing.*;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName BinaryTreeDemo
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/19 21:03
 * @Version 1.0
 * @Description TODO
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "武松");
        HeroNode node3 = new HeroNode(3, "花荣");
        HeroNode node4 = new HeroNode(4, "卢俊义");
        HeroNode node5 = new HeroNode(5, "林冲");
        HeroNode node6 = new HeroNode(6, "晁盖");
        HeroNode node7 = new HeroNode(7, "鲁智深");

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        tree.setRoot(node1);

        System.out.println("前序遍历 : ");
        tree.firstOrderPrint();
        System.out.println("中序遍历 : ");
        tree.middleOrderPrint();
        System.out.println("后序遍历 : ");
        tree.lastOrderPrint();

        System.out.println("查找编号为5的英雄 : ");
        System.out.println("前序查找：");
        HeroNode resultNode1 = tree.firstOrderFind(5);
        if (resultNode1 != null) {
            System.out.println(resultNode1);
        } else {
            System.out.println("未找到该英雄！");
        }
        System.out.println("中序查找：");
        HeroNode resultNode2 = tree.firstOrderFind(5);
        if (resultNode2 != null) {
            System.out.println(resultNode2);
        } else {
            System.out.println("未找到该英雄！");
        }
        System.out.println("后序查找：");
        HeroNode resultNode3 = tree.firstOrderFind(5);
        if (resultNode3 != null) {
            System.out.println(resultNode3);
        } else {
            System.out.println("未找到该英雄！");
        }
        System.out.println("删除节点为5的节点:");
        tree.deleteNode(5);
        tree.middleOrderPrint();

    }
}

// 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void firstOrderPrint() {
        if (this.root != null) {
            root.firstOrderPrint();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 中序遍历
    public void middleOrderPrint() {
        if (this.root != null) {
            root.middleOrderPrint();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 后序遍历
    public void lastOrderPrint() {
        if (this.root != null) {
            root.lastOrderPrint();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 前序查找
    public HeroNode firstOrderFind(int val) {
        if (this.root != null) {
            return root.firstOrderFind(val);
        }
        return null;
    }

    // 中序查找
    public HeroNode middleOrderFind(int val) {
        if (this.root != null) {
            return root.firstOrderFind(val);
        }
        return null;
    }

    // 后序查找
    public HeroNode lastOrderFind(int val) {
        if (this.root != null) {
            return root.firstOrderFind(val);
        }
        return null;
    }

    public void deleteNode(int val) {
        if (this.root != null) {
            if (this.root.getNo() == val) {
                root = null;
            } else {
                root.deleteNode(val);
            }
        } else {
            System.out.println("二叉树为空！");
        }
    }
}

// 定义节点
class HeroNode {
    private String name;
    private int no;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    // 前序遍历
    public void firstOrderPrint() {
        System.out.println(this); // 先输出父节点
        // 左子树
        if (this.left != null) {
            this.left.firstOrderPrint();
        }
        // 右子树
        if (this.right != null) {
            this.right.firstOrderPrint();
        }
    }

    // 中序遍历
    public void middleOrderPrint() {
        // 左子树
        if (this.left != null) {
            this.left.middleOrderPrint();
        }
        System.out.println(this);
        // 右子树
        if (this.right != null) {
            this.right.middleOrderPrint();
        }
    }

    // 后序遍历
    public void lastOrderPrint() {
        // 左子树
        if (this.left != null) {
            this.left.lastOrderPrint();
        }
        // 右子树
        if (this.right != null) {
            this.right.lastOrderPrint();
        }
        System.out.println(this);
    }

    // 前序查找
    public HeroNode firstOrderFind(int val) {
        System.out.println("前序查找");
        if (this.no == val) {
            return this;
        }
        // 左子树
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.firstOrderFind(val);
        }
        if (resultNode != null) {
            return resultNode;
        }
        // 右子树
        if (this.right != null) {
            resultNode = this.right.firstOrderFind(val);
        }
        return resultNode;
    }

    // 中序查找
    public HeroNode middleOrderFind(int val) {
        // 左子树
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.middleOrderFind(val);
        }
        if (resultNode != null) {
            return resultNode;
        }
        System.out.println("中序查找");
        if (this.no == val) {
            return this;
        }

        // 右子树
        if (this.right != null) {
            resultNode = this.right.middleOrderFind(val);
        }
        return resultNode;
    }

    // 后序查找
    public HeroNode lastOrderFind(int val) {

        // 左子树
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.lastOrderFind(val);
        }
        if (resultNode != null) {
            return resultNode;
        }
        // 右子树
        if (this.right != null) {
            resultNode = this.right.lastOrderFind(val);
        }
        if (resultNode != null) {
            return resultNode;
        }
        System.out.println("后序查找");
        if (this.no == val) {
            return this;
        }
        return resultNode;
    }

    // 删除节点
    public void deleteNode(int val) {
        // 当前节点左子节点不为空且是要删除的节点
        if (this.left != null && this.left.no == val) {
            this.left = null;
            return;
        }

        // 右子节点不为空且为删除节点
        if (this.right != null && this.right.no == val) {
            this.right = null;
            return;
        }
        // 左递归删除
        if (this.left != null) {
            this.left.deleteNode(val);
        }
        // 右递归删除
        if (this.right != null) {
            this.right.deleteNode(val);
        }

    }
}