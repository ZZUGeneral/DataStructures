package book.test.lambda;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName DealTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/1 15:44
 * @Version 1.0
 * @Description TODO
 */
public class DealTest {
    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }

    public static void main(String[] args) {
        repeat(10, j -> System.out.println(j));
    }


}
