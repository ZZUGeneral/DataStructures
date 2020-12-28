package com.yhl.datastructure.tree;

import java.lang.reflect.Modifier;
import java.util.EmptyStackException;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName AVLTree
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/19 8:04
 * @Version 1.0
 * @Description TODO
 */
public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTr avltree = new AVLTr();
        for (Integer i : arr) {
            avltree.add(new Node(i));
        }

        System.out.println("中序遍历：");
        avltree.infixOrder();
        System.out.println("平衡之前：");
        System.out.println("树的高度：" + avltree.getRoot().height());
        System.out.println("左子树高度:" + avltree.getRoot().leftHeight());
        System.out.println("右子树高度:" + avltree.getRoot().rightHeight());


    }

}

class AVLTr {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public int delRightTree(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;

    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                return;
            }
            Node parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal = delRightTree(targetNode.right);
                targetNode.value = minVal;
            } else {
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，不能遍历！");
        }

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 获取高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 搜索节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 搜索父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断节点，确定位置
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 右子树高，左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 左子树比右子树高
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先对右节点进行右旋转
                right.rightRotate();
                // 左旋转
                leftRotate();
            } else {
                // 左旋转
                leftRotate();
            }
            // 必须要
            return;
        }

        //左子树高，右旋转
        if (rightHeight() - leftHeight() > 1) {
            // 左子树比右子树高
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先对右节点进行右节点
                right.rightRotate();
                // 左旋转
                leftRotate();
            } else {
                // 左旋转
                leftRotate();
            }
            // 必须要
            return;
        }
    }

    // 左旋转
    private void leftRotate() {
        // 创建新节点
        Node newNode = new Node(value);
        // 把新节点的左子树设为当前节点的左子树
        newNode.left = left;
        // 把新节点的右子树设为当前节点右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值替换为右子节点的值
        value = right.value;
        // 当前节点的右子树设为当前右子树的右子树
        right = right.right;
        // 当前节点的左子树设为新的节点
        left = newNode;
    }

    // 右旋转
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


}