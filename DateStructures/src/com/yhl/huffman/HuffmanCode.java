package com.yhl.huffman;

import sun.font.TrueTypeFont;

import java.io.*;
import java.util.*;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName HuffmanCode
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/2 22:20
 * @Version 1.0
 * @Description TODO
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//
//        List<NodeTwo> nodeTS = getNodes(contentBytes);
//
//        NodeTwo root = huffmanTree(nodeTS);
//        prePrint(root);
//        Map<Byte, String> huffmanCodes1 = getCodes(root);
//        System.out.println("huffman code table:" + huffmanCodes1);
//        System.out.println("huffman code:" + Arrays.toString(zip(contentBytes, huffmanCodes1)));
        byte[] huffmanCodeBytes = huffmanzip(contentBytes);
        System.out.println("huffman code:" + Arrays.toString(huffmanCodeBytes));
        byte[] decodeBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("source string:" + new String(decodeBytes));

    }

    public static void zipFile(String secFile, String desFile) throws IOException {
        FileInputStream is = new FileInputStream(secFile);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        byte[] huffmanBytes = huffmanzip(b);
        OutputStream os = new FileOutputStream(desFile);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(huffmanBytes);

        oos.writeObject(huffmanCodes);
        oos.close();
        os.close();
    }

    public static void unzipFile(String zipFile, String desFile) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(zipFile);
        ObjectInputStream ois = new ObjectInputStream(is);
        byte[] huffmanBytes = (byte[]) ois.readObject();

        Map<Byte, String> huffmanCode4 = (Map<Byte, String>) ois.readObject();
        byte[] bytes = decode(huffmanCode4, huffmanBytes);
        OutputStream os = new FileOutputStream(desFile);
        os.write(huffmanBytes);
        is.close();
        ois.close();
        os.close();
    }


    private static byte[] decode(Map<Byte, String> huffmanCodes3, byte[] huffmanBytes) {
        StringBuilder stringBuilder3 = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder3.append(byteToString(huffmanBytes[i], !flag));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes3.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder3.length(); ) {
            int count = 0;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder3.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            i += count;
            list.add(b);
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    public static String byteToString(byte b, boolean flag) {
        int tmp = b;
        if (flag) {
            tmp |= 256;
        }
        String str = Integer.toBinaryString(tmp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    public static byte[] huffmanzip(byte[] bytes) {
        List<NodeTwo> nodeTS = getNodes(bytes);
        NodeTwo root = huffmanTree(nodeTS);
        prePrint(root);
        Map<Byte, String> huffmanCodes1 = getCodes(root);

        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes1);
        return huffmanCodeBytes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes2) {
        StringBuilder str = new StringBuilder();
        for (Byte b : bytes) {
            String s = huffmanCodes2.get(b);
            str.append(s);
        }

        int len;
        if (str.length() % 8 == 0) {
            len = str.length() / 8;
        } else {
            len = str.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < str.length(); i += 8) {
            String strByte;
            if (i + 8 > str.length()) {
                strByte = str.substring(i);
            } else {
                strByte = str.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;

    }

    private static Map<Byte, String> getCodes(NodeTwo root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    public static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void getCodes(NodeTwo node, String code, StringBuilder stringBuilder1) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }


    }

    public static void prePrint(NodeTwo root) {
        if (root != null) {
            root.preOrderPrint();
        } else {
            System.out.println("空树无法打印！！！");
        }
    }

    public static List<NodeTwo> getNodes(byte[] bytes) {
        ArrayList<NodeTwo> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }

        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new NodeTwo(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static NodeTwo huffmanTree(List<NodeTwo> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            NodeTwo leftNode = nodes.get(0);
            NodeTwo rightNode = nodes.get(1);
            NodeTwo root = new NodeTwo(null, leftNode.value + rightNode.value);
            root.left = leftNode;
            root.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(root);
        }
        return nodes.get(0);
    }
}

class NodeTwo implements Comparable<NodeTwo> {
    Byte data;
    int value; // 节点权值
    NodeTwo left; // 左子节点
    NodeTwo right; // 右子节点

    public NodeTwo(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public String toString() {
        return "NodeTwo{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(NodeTwo o) {
//        System.out.println("=========" + (this.value - o.value));
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
