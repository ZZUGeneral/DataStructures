package com.yhl.algorithm.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HuffmanTree
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/26 21:53
 * @Version 1.0
 * @Description TODO
 */
public class HuffmanTree {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 5, 6, 8, 9, 4, 13, 11, 56};
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        prePrint(root);
    }

    public static void prePrint(Node root) {
        if (root != null) {
            root.preOrderPrint();
        } else {
            System.out.println("空树无法打印！！！");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        Collections.sort(nodes);
        for (Node node : nodes) {
            System.out.println(node);
        }
        System.out.println("-=-=-=-=-=-=-=-=-=");
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node first = nodes.get(0);
            Node second = nodes.get(1);
            Node parent = new Node(first.value + second.value);
            parent.left = first;
            parent.right = second;
            nodes.remove(first);
            nodes.remove(second);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value; // 节点权值
    Node left; // 左子节点
    Node right; // 右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        System.out.println("=========" + (this.value - o.value));
        return this.value - o.value;
    }

    public void preOrderPrint() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderPrint();
        }
        if (this.right != null) {
            this.right.preOrderPrint();
        }
    }
}
