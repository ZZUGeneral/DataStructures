package book.test.reflect.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName CopyOfTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/17 10:58
 * @Version 1.0
 * @Description TODO
 */
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = {"Tom", "Jack", "Harry"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        System.out.println("The Following Call Will Generate an Exception:");
        b = (String[]) badCopyOf(b, 10);
    }

    // 返回对象是对象数组，不能转换为具体的类型
    public static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    // 整数类型数组 int[] 可以被转换成Object ，但是不能转换为对象数组。
    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
