package book.test.reflect.methods;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName MethodTableTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/17 17:35
 * @Version 1.0
 * @Description TODO
 */
public class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method sequare = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, sequare);
        printTable(1, 10, 10, sqrt);
    }

    // 计算平方值
    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) {
        // 打印方法签名
        System.out.println(f);
        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f\n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
