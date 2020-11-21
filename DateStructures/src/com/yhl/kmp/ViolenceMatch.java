package com.yhl.kmp;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ViolenceMatch
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/15 21:46
 * @Version 1.0
 * @Description TODO
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "尚硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅谷你好";
        String str2 = "尚硅谷你尚硅谷你";
//        int index = violenceMatch(str1, str2);
        int index = kmp(str1, str2);
        System.out.println("index= " + index);

    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int len1 = str1.length();
        int len2 = str2.length();

        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == len2) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int kmp(String str1, String str2) {
        int[] next = kmpNext(str2);
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }

        }
        return -1;

    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为 1，部分匹配值就是 0

        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当 dest.charAt(i) != dest.charAt(j)时，我们需要从 next[j-1]中获取新的 j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //dest.charAt(i) == dest.charAt(j)时，匹配值加 1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
